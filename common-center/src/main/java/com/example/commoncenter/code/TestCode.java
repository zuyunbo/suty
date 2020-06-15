package com.example.commoncenter.code;

public class TestCode {


    public static void main(String[] args) {

        AutoInterface autoInterface = new AutoInterfaceImpl() {
            @Override
            public GenerateCode getGenerateCodes() {
                return super.getGenerateCodes();
            }
        };
        autoInterface.connectionJDBC();
        GenerateCode generateCodes = autoInterface.getGenerateCodes();
        generateCodes.isToExecute();

    }
}
