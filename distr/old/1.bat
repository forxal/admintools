REM =========== top ===========
@ECHO OFF
ECHO The current directory is %CD%
PAUSE
ECHO The running batch file is %0
PAUSE
FOR /F %%I IN ("%0") DO SET BATDIR=%%~dpI
ECHO The batch file is located in directory %BATDIR%
ECHO.
CD /D %BATDIR%
CD
PAUSE
SET BATDIR=
:END
REM ========= bottom ===========