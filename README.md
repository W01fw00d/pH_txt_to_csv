## Converts pH measurements in .txt into .csv

- This program UI, code variables and build/manual.txt is written in [Catalan](https://en.wikipedia.org/wiki/Catalan_language) only

This task parses a .txt file with a format like `build/entrada/exemple.TXT` (build/input/example)
This input file is the output from a [`Mettler Toledo pH meter`](https://www.mt.com/my/en/home/products/Laboratory_Analytics_Browse/pH-meter.html)

- It orders the data by hour of measurement and pH value
- It removes innecesary additional data
- It outputs a .csv file

## How to use the build

1. Open the .jar file inside build
2. A window will pop-up. Click inside the box next to "Arxiu d'entrada" (input file) in order to see a dropdown with all the files available in the "entrada" (input) folder
3. Select a file. The task will name the output .csv file with the input file name by default. You can customize that name using the textbok "Arxiu de sortida" (output file)
4. Click in "Crear arxiu" (create file) to process the choosen file and create a new file in the "sortida" (output) folder
5. A message displaying "Arxiu \***\*\_\_\_\_\*\*** creat" ("\***\*\_\_\_\_\*\*** file created") will popup if the process was succesful
