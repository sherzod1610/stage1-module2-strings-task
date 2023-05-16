package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        MethodSignature methodSignature = new MethodSignature(getMethodName(signatureString), getArgumentList(signatureString));
        methodSignature.setMethodName(getMethodName(signatureString));
        methodSignature.setReturnType(getReturnType(signatureString));
        methodSignature.setAccessModifier(getAccessModifier(signatureString));
        return methodSignature;
    }

    public String getAccessModifier(String signatureString) {
        String[] splittedSignatureString = signatureString.split(" ");
        return splittedSignatureString[0];
    }

    public String getReturnType(String signatureString) {
        String[] splittedSignatureString = signatureString.split(" ");
        return splittedSignatureString[1];
    }

    public String getMethodName(String signatureString) {
        int index = signatureString.indexOf('(');
        String untilMethodName = signatureString.substring(0, index);
        String[] splittedSignatureString = untilMethodName.split(" ");
        return splittedSignatureString[2];
    }

    public List<MethodSignature.Argument> getArgumentList(String signatureString) {
        int index = signatureString.indexOf('(');
        String insideOfBrackets = signatureString.substring(index + 1, signatureString.length() - 1);
        String[] splittedString = insideOfBrackets.split(",");
        MethodSignature.Argument argument = new MethodSignature.Argument();
        List<MethodSignature.Argument> argumentList = new ArrayList<>();
        for (String s : splittedString) {
            argument.setType(s.split(" ")[0]);
            argument.setName(s.split(" ")[1]);
            argumentList.add(argument);
        }
        return argumentList;
    }
}
