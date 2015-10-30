#!/bin/bash

# --------------------------------------------------
# 				Helper Method's
# --------------------------------------------------

# Method start a docker machine
# @param - name of the docker machine
function startDocker() 
{
	docker-machine start $1 &
	wait
	echo "Docker machine $1 was started!!!"
}

# Method start a communication with this machine
# param - docker machine name
function startCommunication()
{
	if [ $1 == "dice" ]; then
		eval "$(docker-machine env dice)" &
	elif [ $1 == "dice2" ]; then
		eval "$(docker-machine env dice2)" &
	else
		echo "unknow machine"
	fi
	
	wait
	echo "Started communication with our first docker machine"
}

# Method load the containers in the virtual machines
# @param - image name
#
function loadContainer()
{
	docker run -p 4567:4567 $1 &
	sleep 5
	echo "Started our first container"
}

# --------------------------------------------------
#				Script Beginn
# --------------------------------------------------
echo "STARTED OUR SCRIPT"

# this script started our dice applications of port 4567

# our container virtual machine names
dice="dice"
dice2="dice2"

# our container app name
app="app"

# --------------------------------------------------
#			Start first dice application
# --------------------------------------------------
# start the dice virtual machines
startDocker $dice

# communication with this machine
startCommunication $dice

# load the containers in the virtual machines
loadContainer $app

echo pwd

# --------------------------------------------------
#			Start second dice application
# --------------------------------------------------
# start the dice virtual machines
startDocker $dice2

# communication with this machine
startCommunication $dice2

# load the containers in the virtual machines
loadContainer $app

echo "EXECUTION SUCCESSFUL"
