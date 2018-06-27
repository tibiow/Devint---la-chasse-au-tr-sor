dir /s /B dvt\*.java > sources.txt
javac -cp ..\..\VocalyzeSIVOX\bin\SI_VOX.jar;..\ressources\lib\jlayer-1.0.1-1.jar -d ..\bin @sources.txt
pause
