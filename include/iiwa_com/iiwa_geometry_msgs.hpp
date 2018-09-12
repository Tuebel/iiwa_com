#pragma once
#include <geometry_msgs/TransformStamped.h>
#include <geometry_msgs/WrenchStamped.h>
#include <iiwa_msgs.pb.h>

namespace iiwa_com
{
/*!
Converts the iiwa pose message to a ROS pose
*/
geometry_msgs::TransformStamped from_iiwa_pose(const CartesianPose &pose);

/*!
Converts the iwa force message to a ROS wrench
*/
geometry_msgs::WrenchStamped from_iiwa_force(const CartesianForce &force);
} // namespace iiwa_com
