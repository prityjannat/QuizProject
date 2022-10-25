package McqQuiz;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class startproject {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add Quiz");
        System.out.println("2. Start Quiz");
        String input = scanner.nextLine();
       // test
        switch (input)
        {
            case "1" :
                char ch = 'n';
                do {
                    JSONParser jsonParser = new JSONParser();
                    Object obj = jsonParser.parse(new FileReader("./src/main/resources/AddQuestionList.json"));
                    JSONArray jsonArray = (JSONArray) obj;
                    JSONObject jsonObject = new JSONObject();
                    System.out.println("Please add a question here");
                    jsonObject.put("Question" , scanner.nextLine());
                    System.out.println("Input Options");
                    System.out.println("Option a: ");
                    jsonObject.put("Option a " , scanner.nextLine());
                    System.out.println("Option b: ");
                    jsonObject.put("Option b " , scanner.nextLine());
                    System.out.println("Option c: ");
                    jsonObject.put("Option c " , scanner.nextLine());
                    System.out.println("Option d: ");
                    jsonObject.put("Option d " , scanner.nextLine());
                    System.out.println("Please input the correct answer");
                    jsonObject.put("answer" , scanner.nextLine());
                    jsonArray.add(jsonObject);

                    FileWriter fileWriter = new FileWriter("./src/main/resources/AddQuestionList.json");
                    fileWriter.write(jsonArray.toJSONString());
                    fileWriter.flush();
                    System.out.println("Quiz saved at the database.Do you want to add more.(y/n)");
                    ch = scanner.nextLine().charAt(0);

                } while (ch == 'y');
                break;

            case "2" :
                System.out.println("You will be asked 5 questions, each questions has 1 marks");
                int count = 0;
                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(new FileReader("./src/main/resources/AddQuestionList.json"));
                JSONArray jsonArray = (JSONArray) obj;
//               System.out.println(jsonArray.size());
                for (int i = 1; i<=5; i++){
                    Random r = new Random();
                    int res = r.nextInt(jsonArray.size());
                    JSONObject jsonObject = (JSONObject) jsonArray.get(res);
                    String ques = (String) jsonObject.get("Question");
                    System.out.println(i+". "+ques);
                    String option1 = (String) jsonObject.get("Option a ");
                    System.out.println("a. "+option1);
                    String option2 = (String) jsonObject.get("Option b ");
                    System.out.println("b. "+option2);
                    String option3 = (String) jsonObject.get("Option c ");
                    System.out.println("c. "+option3);
                    String option4 = (String) jsonObject.get("Option d ");
                    System.out.println("d. "+option4);
                    System.out.println("Answer");
                    String useranswer = scanner.nextLine();
                    String jsonanswer = (String) jsonObject.get("answer");
                    if (useranswer.equals(jsonanswer)){
                        System.out.println("Correct!");
                        count++;
                    }
                    else {
                        System.out.println("Not Correct!");
                    }


                }
                System.out.println("You got "+count+" out of 5");
                break;

            default:
                System.exit(0);

        }


    }
}
