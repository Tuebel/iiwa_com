#include <iiwa_com/iiwa_geometry_msgs.hpp>
#include <tf2/LinearMath/Quaternion.h>
#include <tf2_geometry_msgs/tf2_geometry_msgs.h>

namespace iiwa_com
{
geometry_msgs::TransformStamped from_iiwa_pose(const CartesianPose &pose)
{
  geometry_msgs::TransformStamped result;
  // fill the transform
  result.transform.translation.x = pose.x();
  result.transform.translation.y = pose.y();
  result.transform.translation.z = pose.z();
  tf2::Quaternion quat;
  // sets ZYX rotation, roll:X=C, pitch:Y=B, yaw:Z=A
  quat.setRPY(pose.c(), pose.b(), pose.a());
  result.transform.rotation = tf2::toMsg(quat);
  // fill header
  result.child_frame_id = pose.child_frame();
  result.header.frame_id = pose.base_frame();
  result.header.stamp = ros::Time::now();
  return result;
}

geometry_msgs::WrenchStamped from_iiwa_forch(const CartesianForce &force){
  geometry_msgs::WrenchStamped result;
  // fill the vectors
  result.wrench.force.x = force.f_x();
  result.wrench.force.y = force.f_y();
  result.wrench.force.z = force.f_z();
  result.wrench.torque.x = force.t_x();
  result.wrench.torque.y = force.t_y();
  result.wrench.torque.z = force.t_z();
  // fill header
  result.header.frame_id = force.frame();
  result.header.stamp = ros::Time::now();
  return result;
}
} // namespace iiwa_com