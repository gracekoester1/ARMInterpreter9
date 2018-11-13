package com.example.aweso.arminterpreter;

import java.util.LinkedList;

public class Instruction
{
    private String magicTrick;
    private String destinationName;
    private LinkedList<String> theInputNames = new LinkedList<String>();

    public Instruction(String instruction)
    {
        int locationOfFirstSpace = instruction.indexOf(' ');
        this.magicTrick = instruction.substring(0, locationOfFirstSpace);
        String theRest = instruction.substring(locationOfFirstSpace).trim(); //"   X0,  X1,X2" -> "X0,  X1,X2"
        String[] parts = theRest.split(","); // ["X0", "  X1", "X2"]
        this.destinationName = parts[0].trim(); //"X0
        this.theInputNames.add(parts[1].trim()); //"X1"
        this.theInputNames.add(parts[2].trim()); //"X2"
    }

    public void execute()
    {
        if(this.magicTrick.equalsIgnoreCase("ADD"))
        {
            Register destinationRegister = ARMap.findRegisterWithName(this.destinationName);
            Register input2Register = ARMap.findRegisterWithName(theInputNames.get(0));
            Register input1Register = ARMap.findRegisterWithName(theInputNames.get(1));
            destinationRegister.setValue(input2Register.getValue() + input1Register.getValue());
        }
        else if(this.magicTrick.equalsIgnoreCase("SUB"))
        {
            //write the code to Subtract input1 from input2 and store the result in destinationName
            Register destinationRegister = ARMap.findRegisterWithName(this.destinationName);
            Register input2Register = ARMap.findRegisterWithName(theInputNames.get(0));
            Register input1Register = ARMap.findRegisterWithName(theInputNames.get(1));
            destinationRegister.setValue(input2Register.getValue() - input1Register.getValue());
        }
        else if(this.magicTrick.equalsIgnoreCase("ADDI"))
        {
            //write the code to ADD input2 to an immediate value and store the result in destinationName
            Register destinationRegister = ARMap.findRegisterWithName(this.destinationName);
            Register input2Register = ARMap.findRegisterWithName(theInputNames.get(0));

            destinationRegister.setValue(input2Register.getValue() + immediateValue);
        }
        else if(this.magicTrick.equalsIgnoreCase("SUBI"))
        {
            //write the code to Subtract the immediate value in input1 from input2 and store the result in destinationName
            Register destinationRegister = ARMap.findRegisterWithName(this.destinationName);
            Register input2Register = ARMap.findRegisterWithName(theInputNames.get(0));

            destinationRegister.setValue(input2Register.getValue() - immediateValue);
        }
    }
}
