Instructions for compiling and running:

Compiling the program:

From the command line change directories into the directory that this README file is located in.

$ cd laberge-tyler-9192

Then run the following commands to compile all source files.

On Linux run these commands:
$ find -name "*.java" > sources.txt
$ javac @sources.txt

On Windows run these commands
> dir /s /B *.java > sources.txt
> javac @sources.txt

Running the program:

Change directories into the source directory.

$ cd laberge-tyler-9192/src

Run the App class.

$ java com.tylerlaberge.main.App "fully_qualified_input_file_path" "output_file_path"

Contact information:

email: tyler.laberge@maine.edu
phone: 207-329-5760