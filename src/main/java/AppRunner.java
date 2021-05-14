import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.EmployeeDetails;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AppRunner {

  private static final String ZIP_REGEX = "^[0-9]{5}(?:-[0-9]{4})?$";

  public static void main(String[] args) throws IOException, ParseException {
    InputStream is = AppRunner.class.getClassLoader().getResourceAsStream("data.json");

    JSONParser parser = new JSONParser();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(is);
    Iterator<JsonNode> arrayIterator = jsonNode.iterator();
    List<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();
    List<String> outputList = new ArrayList<String>();

    while (arrayIterator.hasNext()) {
      JsonNode elementInX = arrayIterator.next();
      EmployeeDetails employeeDetails = mapper.treeToValue(elementInX, EmployeeDetails.class);

      if (isDuplicateRecord(employeeDetailsList, employeeDetails)) {
        collectDuplicateRecords(employeeDetailsList, outputList, employeeDetails);
      } else if (StringUtils.isBlank(employeeDetails.getName())) {
        outputList.add(employeeDetails.getId());
      } else if (StringUtils.isBlank(employeeDetails.getAddress())) {
        outputList.add(employeeDetails.getId());
      } else if (isZipWrong(employeeDetails)) {
        outputList.add(employeeDetails.getId());
      }
      employeeDetailsList.add(employeeDetails);
    }

    System.out.println("output for - https://github.com/syniti/software-engineer-questions");
    outputList.forEach(System.out::println);

  }

  private static boolean isZipWrong(EmployeeDetails employeeDetails) {
    return StringUtils.isBlank(employeeDetails.getZip()) || !isValidUsZip(
        employeeDetails.getZip());
  }

  private static boolean isValidUsZip(String zip) {
    Pattern pattern = Pattern.compile(ZIP_REGEX);
    Matcher matcher = pattern.matcher(zip);
    return matcher.matches();
  }

  private static void collectDuplicateRecords(List<EmployeeDetails> employeeDetailsList,
      List<String> outputList, EmployeeDetails employeeDetails) {
    int indexOfExistingData = employeeDetailsList.indexOf(employeeDetails);
    EmployeeDetails existingEmpRecord = employeeDetailsList.get(
        indexOfExistingData);
    outputList.add(existingEmpRecord.getId());
    outputList.add(employeeDetails.getId());
  }

  private static boolean isDuplicateRecord(List<EmployeeDetails> employeeDetailsList,
      EmployeeDetails employeeDetails) {
    return employeeDetailsList.size() > 0 && employeeDetailsList.contains(employeeDetails);
  }

}


