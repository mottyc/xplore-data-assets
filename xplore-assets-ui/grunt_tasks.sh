#!/bin/sh
# Build frontend single page application using grunt build tool

WORK_DIR=$1
COPY_DIR=$2

echo "Arg1: " $WORK_DIR
echo "Arg2: " $COPY_DIR

cd $WORK_DIR

echo "grunt " $WORK_DIR " build"
grunt build

echo "copy " $WORK_DIR " -> " $COPY_DIR


# Copy the client side SPA to the WAR webapp folder for packaging
cp -R $WORK_DIR/dist/. $COPY_DIR

# Display Done
echo "Done"
echo ""