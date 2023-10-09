import java.util.*;
import java.util.concurrent.CompletableFuture;

public class QueryValidator {

    private static Map<String, String> columns;

    public QueryValidator() {
        columns = new HashMap<>();

        columns.put("consignerName", "String");
        columns.put("consigneeName", "String");
        columns.put("locationName", "String");
        columns.put("locationCode", "String");
        columns.put("locationCity", "String");
        columns.put("state", "String");
        columns.put("pincode", "String");
        columns.put("country", "String");
        columns.put("routeCode", "String");
        columns.put("plantCode", "String");
        columns.put("key1", "String");
        columns.put("key2", "String");
        columns.put("key3", "String");
        columns.put("businessVertical", "String");
        columns.put("productType", "String");
        columns.put("containerType", "String");
        columns.put("dummy1", "Integer");
        columns.put("dummy2", "Long");
        columns.put("dummy3", "FLoat");
        columns.put("dummy4", "Double");
        columns.put("dummy5", "Boolean");

    }


    public static void main(String[] args) {
        QueryValidator ob = new QueryValidator();
        String expression = "  consignerName == 'pranjul jani' || consigneeName == 'Suresh' && consigneeName == 'bombay' ";
        String test1 = " (consignerName) == 20)";

        String test2 = "consignerName == 'pranjul jani' && consigneeName == 'Suresh Patel' && (locationName == 'bombay' || locationName == 'Goa') && pincode == '20' || pincode == '10')";

        String test3 = "consignee_name == 'Test User' && (consignerName == 'Test User 2' || consignerName == 'Test User 2')";


        List<Runnable> runnableList = new ArrayList<>();

        Runnable r1 = () -> ob.balanceParenthesis(test3);
        Runnable r2 = () -> ob.evaluateExpression(test3);

        runnableList.add(r1);
        runnableList.add(r2);

        CompletableFuture<?>[] completableFutures = runnableList.stream()
                .map(CompletableFuture::runAsync)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(completableFutures).join();
    }


    private boolean balanceParenthesis(String expression) {
        expression = expression.trim();
        char[] expressionArray = expression.toCharArray();

        int count = 0;

        for (char ch : expressionArray) {
            if (ch == '(') {
                count += 1;
            }
            else if (ch == ')') {
                count -= 1;
            }
        }

        if (count != 0) {
            System.out.println("Malformed expresion parenthesis not balanced");
            throw new RuntimeException("Malformed exception");
        }

        return true;
    }

    private boolean evaluateExpression(String expression) {

        expression = expression.trim();
        if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {
            expression = expression.substring(1, expression.length() - 1);
        }

        char[] expressionArray = expression.toCharArray();
        int elementIndex = 0;

        StringBuffer sb = new StringBuffer();
        String currentColumn = "";

        for (int i=0;i<expressionArray.length;i++) {
            if (elementIndex % 4 == 0) {
                while (i < expressionArray.length && expressionArray[i] == ' ') {
                    i++;
                }
                StringBuffer currElement = new StringBuffer();
                while (i < expressionArray.length && expressionArray[i] != ' ') {
                        currElement.append(expressionArray[i]);
                        i += 1;
                }
                String current;
                if (currElement.charAt(0) == '(') {
                    current = currElement.toString().substring(1, currElement.length()).trim();
                }
                else {
                    current = currElement.toString();
                }
                if (!columns.containsKey(current)) {
                    System.out.println("Invalid column Name " + current);
                    return false;
                }
                sb.append(currElement + " ");
                currentColumn = current;
                elementIndex += 1;
            }
            else if (elementIndex % 4 == 1) {
                while (i < expressionArray.length && expressionArray[i] == ' ') {
                    i += 1;
                }
                StringBuffer currElement = new StringBuffer();
                while (i < expressionArray.length && expressionArray[i] != ' ') {
                    currElement.append(expressionArray[i]);
                    i += 1;
                }
                if (!(currElement.toString().equals("==") || currElement.toString().equals("!="))) {
                    System.out.println("Invalid relational operator " + currElement);
                    return false;
                }
                sb.append(currElement + " ");
                elementIndex += 1;
            }
            else if (elementIndex % 4 == 2) {
                while (i < expressionArray.length && expressionArray[i] == ' ') {
                    i++;
                }
                String dataType = columns.get(currentColumn);
                StringBuffer currElement = new StringBuffer();
                if (dataType.equals("String")) {
                    if(i < expressionArray.length) {
                        currElement.append(expressionArray[i]);
                        i += 1;
                    }
                    while(i < expressionArray.length && expressionArray[i] != '\'') {
                        currElement.append(expressionArray[i]);
                        i += 1;
                    }
                    if(i < expressionArray.length) {
                        currElement.append(expressionArray[i]);
                        i += 1;
                    }
                    if (!(currElement.charAt(0) == '\'' && currElement.charAt(currElement.length()-1) == '\'')) {
                        System.out.println("Invalid Column Value " + currElement + " for Column " + currentColumn);
                        return false;
                    }
                }
                if (i < expressionArray.length && expressionArray[i] == ')') {
                    currElement.append(expressionArray[i]);
                    i += 1;
                }
                sb.append(currElement + " ");
                elementIndex += 1;
            }
            else if (elementIndex % 4 == 3){
                while (i < expressionArray.length && expressionArray[i] == ' ') {
                    i++;
                }
                StringBuffer currElement = new StringBuffer();
                while (i < expressionArray.length && expressionArray[i] != ' ') {
                    currElement.append(expressionArray[i]);
                    i += 1;
                }
                if (!(currElement.toString().equals("&&") || currElement.toString().equals("||"))) {
                    System.out.println("Invalid logical operator " + currElement);
                    return false;
                }
                sb.append(currElement + " ");
                elementIndex += 1;
            }
        }
        System.out.println("-" + sb.toString().trim() + "-");
        if(elementIndex % 4 != 3) {
            System.out.println("Incomplete expression");
            return false;
        }
        return true;
    }


}
