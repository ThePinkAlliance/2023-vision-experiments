This project makes use of camera to obtain AprilTag angle and distance. It can use both a PhotonVision or Limelight camera, 
and can do simulation via the SmartDashboard.

Setup:
To change between Limelight and PhotonVision change line 12/13 in CameraSubsystem to create the proper camera.

Simulation:
Use the SmartDashboard or Glass to set values in the SmartDashboard keys.

Target robot: PizzaBot with a RPI, Limelight, or other controller running PhotonVision or Limelight.

Robot setup:
* PhotonVision camera must be called "main" and the default pipeline be set for AprilTags
* Limelight camera must be name "limelight"

To use in PizzaBot:
1. Deploy to PizzaBot.
1. Set to Autonomous mode.
1. Use an AprilTag and move closer and farther away from the robot. The robot will want to stay within 1m of the tag.

To use in simulation:
1. Run in Simulation mode.
1. Select the Sim UI for the simulation.
1. Start autonomous mode.
1. Set the camera values in the Network Tables window under SmartDashboard.