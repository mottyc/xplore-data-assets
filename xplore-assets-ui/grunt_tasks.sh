#!/bin/sh
# Build frontend single page application using grunt build tool

WORK_DIR=$1
COPY_DIR=$2
TASK=$3

cd $WORK_DIR

echo "grunt " $WORK_DIR $TASK
grunt $TASK


echo "copy " $WORK_DIR " -> " $TCOPY_DIR


# Copy the client side SPA to the WAR webapp folder for packaging
cp -R $WORK_DIR/dist/. $COPY_DIR

# Display Done
echo "Done"
echo ""