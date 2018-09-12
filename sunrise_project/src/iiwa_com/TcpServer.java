package iiwa_com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.task.ITaskLogger;

/**
 * Accepts incoming connections and creates Communicators for them.
 * 
 * @author Tim Übelhör
 * 
 */
public class TcpServer implements Runnable {

	private final LBR robot;
	private final ITaskLogger logger;
	private final ServerSocket server;
	private final List<Communicator> communicators;
	private final List<Thread> com_threads;

	public TcpServer(int port, ITaskLogger logger, LBR robot)
			throws IOException {
		this.robot = robot;
		this.logger = logger;
		// build the server
		server = new ServerSocket(port);
		communicators = new ArrayList<Communicator>();
		com_threads = new ArrayList<Thread>();
	}

	@Override
	public void finalize() {
		// make sure the tcp server has been closed to avoid the robot reboot
		try {
			close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void close() throws IOException {
		// closed the server
		if (!server.isClosed()) {
			server.close();
		}
		for (Communicator communicator : communicators) {
			communicator.close();
		}
		logger.info("closed the server");
	}

	@Override
	public void run() {
		logger.info("started listening on port " + server.getLocalPort());
		while (!server.isClosed()) {
			try {
				Socket socket = server.accept();
				Communicator communicator = new Communicator(socket, logger,
						robot);
				Thread com_thread = new Thread(communicator);
				com_thread.start();
				communicators.add(communicator);
				com_threads.add(com_thread);
				logger.info("added new communicator on "
						+ socket.getInetAddress());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}
}
