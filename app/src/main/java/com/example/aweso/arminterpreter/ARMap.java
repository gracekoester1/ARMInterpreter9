package com.example.aweso.arminterpreter;

import android.widget.EditText;

public class ARMap
{
    private static String[] instructions = {"ADD", "SUB", "ADDI", "SUBI"};
    private static int[] instructionMappings = {1112, 1624};
    public static Register[] registers = new Register[31];

    public static void init()
    {
        for(int i = 0; i < ARMap.registers.length; i++)
        {
            ARMap.registers[i] = new Register("X" + i);
        }
    }

    public static void getImmediateValue()
    {
        this.getImmediateValue[30] = (EditText)this.findViewById(R.id.x30ET);
        int immediateValue = this.getImmediateValue();
    }

    public static Register findRegisterWithName(String nameOfRegister)
    {
        for(Register r : ARMap.registers)
        {
            if(r.getHumanReadableName().equalsIgnoreCase(nameOfRegister))
            {
                return r;
            }
        }
        return null;
    }

    public static BinaryValue lookupInstruction(String instruction)
    {
        instruction = instruction.toUpperCase();
        for(int i = 0; i < instructions.length; i++)
        {
            if(instructions[i].equals(instruction))
            {
                return new BinaryValue(decimalTo11BitBinary(instructionMappings[i]));
            }
        }
        return null;  //we assume every mapping is found, so this should never get hit!
    }

    public static String decimalTo5BitBinary(int opCode)
    {
        return ARMap.decimalToNBitBinary(opCode, 5);
    }

    private static String decimalTo11BitBinary(int opCode)
    {
        return ARMap.decimalToNBitBinary(opCode, 11);
    }

    private static String decimalToNBitBinary(int opCode, int numBits)
    {
        String answer = "";
        while(opCode > 0)
        {
            answer = (opCode%2) + answer;
            opCode /= 2;
        }

        for(int i = answer.length(); i < numBits; i++)
        {
            answer = 0 + answer;
        }
        return answer;
    }
}
