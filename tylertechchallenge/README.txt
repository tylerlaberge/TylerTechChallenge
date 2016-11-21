# Linux
$ find -name "*.java" > sources.txt
$ javac @sources.txt

:: Windows
> dir /s /B *.java > sources.txt
> javac @sources.txt

Then

$ cd src
$ java com.tylerlaberge.main.App -input_file_path -output_file_path