package utils;

import service.AirlinesService;

import java.util.Scanner;

public class Menu {

    public Scanner in = new Scanner(System.in);
    public AirlinesService airlinesService = new AirlinesService();

    public void mainMenu() {

        int op = -1;
        while (op != 0) {
            printLineSeparator();
            //printa as opçoes do menu
            System.out.println("Digite a opção que você deseja:" +
                    "\n1 - Cadastrar Vôo" +
                    "\n2 - Cadastrar Passageiro" +
                    "\n3 - Ver Vôo" +
                    "\n4 - Ver Passageiro" + //chama um submenu
                    "\n5 - Alterar Vôo" +
                    "\n6 - Alterar Passageiro" +
                    "\n7 - Excluir Vôo" +
                    "\n8 - Excluir Passageiro" +
                    "\n0 - Sair");
            op = in.nextInt();

            switch (op) {
                case 1: //1 - Cadastrar Vôo
                    airlinesService.saveFlight();
                    break;
                case 2: //2 - Cadastrar Passageiro
                    airlinesService.savePassanger();
                    break;
                case 3: //3 - Ver Vôo
                    subMenu(op);
                    break;
                case 4: //4 - Ver Passageiro
                    break;
                case 5: //5 - Alterar Vôo
                    break;
                case 6: //6 - Alterar Passageiro
                    break;
                case 7: //7 - Excluir Vôo
                    break;
                case 8: //8 - Excluir Passageiro
                    break;
                case 0: //0 - Sair
                    break;
                default:
                    System.out.println("Resposta inválida");
                    break;
            }
        }
    }

    public void printLineSeparator(){
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------");
    }

    public void subMenu(int op) {

        if (op == 3) { //opcao de ver voos
            printLineSeparator();
            System.out.println("Digite a sub opção desejada \n"
                    + "1 - Ver todos os vôos\n" +
                      "2 - Ver os vôos com mais passageiros (assentos ocupados)\n" +
                      "3 - Ver o vôo com menos passageiros\n" +
                      "4 - Ver o vôo mais longo\n" +
                      "5 - Ver o vôo mais curto\n" +
                      "6 - Ver a média de ocupação dos vôos");
            int subOp = in.nextInt();
            switch (subOp) {
                case 1: //1 - Ver todos os vôos
                    System.out.println(airlinesService.getAllFlights());
                    break;
                case 2: //2 - Ver os vôos com mais passageiros (assentos ocupados)

                    break;
                case 3: //3 - Ver o vôo com menos passageiros

                    break;
                case 4: //4 - Ver o vôo mais longo

                    break;
                case 5: //5 - Ver o vôo mais curto

                    break;
                case 6: //6 - Ver a média de ocupação dos vôos

                    break;
            }
        }
        else if(op==4){
            return;
        }
    }
}
