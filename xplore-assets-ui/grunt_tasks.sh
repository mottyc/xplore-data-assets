#!/bin/sh
# Build frontend single page application using grunt build tool

WORK_DIR=$1
TASK=$2

cd $WORK_DIR

echo "grunt " $WORK_DIR $TASK

grunt $TASK

# Display Done
echo "Done"
echo ""