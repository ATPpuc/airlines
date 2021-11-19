package utils;

import service.AirlinesService;

import java.util.Scanner;

public class Menu {

    public Scanner in = new Scanner(System.in);
    public AirlinesService airlinesService = new AirlinesService();



    public void mainMenu() {

        int op = -1;
        while (op != 0) {
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
                    break;
                case 3: //3 - Ver Vôo
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
}
