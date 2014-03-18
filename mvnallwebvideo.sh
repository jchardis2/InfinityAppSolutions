cd /home/jchardis/git/InfinityAppSolutions/infinityappsolutions.lib
mvn clean install

if [ "$?" != "0" ]; then
	echo "Error. Building halted."
	exit 1
fi

cd /home/jchardis/git/InfinityAppSolutions/infinityappsolutions.server.lib
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi

cd /home/jchardis/git/InfinityAppSolutions/infinityappsolutions.lib.webvideo
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi

cd /home/jchardis/git/InfinityAppSolutions/infinityappsolutions.lib.server.webvideo
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi


cd /home/jchardis/git/InfinityAppSolutions/WebVideo
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi

cd /home/jchardis/git/InfinityAppSolutions/HomeVideo
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi
