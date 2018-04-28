# Signal Generator (2018)
Java application generating different signals and showing their graphs using JFreeChart library.

-------------------

### Instruction

##### CLI arguments

You need to provide 3 arguments (to show single signal) or 6 arguments (to show the result of mathematical operation performed on two signals).

CLI arguments pattern:
- 3 arguments:
[g/f] [gen_number/input_file] [output_file]
- 6 arguments:
[g/f] [gen_number_1/input_file_1] [g/f] [gen_number_2/input_file_2] [operation] [output_file]

If you choose ’g’ (generator) as input signal, you need to enter a number from  1 to 11, corresponding to one of available generators:
- 1 - Uniform Noise
- 2 - Gaussian Noise
- 3 - Sine Wave
- 4 - Half Wave Rectified Sine
- 5 - Full Wave Rectified Sine
- 6 - Pulse Wave
- 7 - Symmetrical Pulse Wave
- 8 - Triangle Wave
- 9 - Unit Step
- 10 - Kronecker Delta
- 11 - Impulse Noise

If you choose ’f’ (file) as input signal, you need to enter a path to it's file.

If you choose two input signals, the output signal is the result of mathematical operation performed on input signals.
You can choose one of these operation:
- ADD - addition
- SUB - subtraction
- MUL - multiplication
- DIV - division

##### Configuration files
Parameters specific for each generator can be configured in files located in *config* directory (parameters not used by specific signal are skipped):
- *first.properties* - first generator parameters
- *second.properties* - second generator parameters


Bins number for histogram can be configured in another file located in *config* directory:
- *histogram.properties*  


