cd /home/jchardis/git/InfinityAppSolutions/WebVideo
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi

