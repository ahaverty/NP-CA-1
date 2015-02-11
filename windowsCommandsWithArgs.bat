rmdir /S /Q bin
mkdir bin
javac -d bin -cp lib\commons-net-3.3-sources.jar src\txtclock\*.java
copy src\txtclock\words_eng.txt bin\ie\dit\student\haverty\alan\txtclock\
set /P args=Enter arguments:
java -cp bin ie.dit.student.haverty.alan.txtclock.TxtClock %args%
PAUSE