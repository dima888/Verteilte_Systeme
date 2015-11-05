#!/bin/sh

# --------------------------------------------------
# 				Docker utility Method's
# --------------------------------------------------

# Method start a docker machine
# @param - name of the docker machine
function startDocker()
{
docker-machine start $1
wait
echo "			------- Docker machine $1 was started!!! -------"
}

# Method start a communication with this machine
# param - docker machine name
function startCommunication()
{
if [ $1 == "dice" ]; then
	eval "$(docker-machine env dice)"
elif [ $1 == "dice2" ]; then
	eval "$(docker-machine env dice2)"
elif [ $1 == "game" ]; then
	eval "$(docker-machine env game)"

else
echo "			------- unknow machine -------"
fi

wait
echo "			------- Started communication with $1 docker machine -------"
}

# Method load the containers in the virtual machines
# @param - image name
function loadContainer()
{
docker run -p 4567:4567 $1
echo "			------- Stopped-Container -------"
}