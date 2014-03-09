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

cd /home/jchardis/git/InfinityAppSolutions/infinityappsolutions.webterms.lib
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi

cd /home/jchardis/git/InfinityAppSolutions/WebTerms
mvn clean install

if [ "$?" != "0" ]; then
        echo "Error. Building halted."
        exit 1
fi



