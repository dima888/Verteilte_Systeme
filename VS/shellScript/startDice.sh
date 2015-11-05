#!/bin/sh

# for maven
#source shellScript/util.sh

# for shell
source util.sh

virtual_box_name="dice"
container_name="app"

# --------------------------------------------------
#			Start application
# --------------------------------------------------
echo "			------- TRY TO START APPLICATION -------"
# start the dice virtual machines
startDocker $virtual_box_name 

# communication with this machine
startCommunication $virtual_box_name 

# load the containers in the virtual machines
loadContainer $container_name

echo "			------- EXECUTION SUCCESSFUL -------"
