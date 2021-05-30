package sample;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import static sample.Main.primaryStage;

public class Controller {

    @FXML
    private ImageView img_logo;

    private void setImg_logo(Image img_logo) {
        this.img_logo.setImage(img_logo);
    }

    @FXML
    private Button btn_view_logo;

    @FXML
    private Button btn_change_logo;

    @FXML
    private Text txt_error;

    @FXML
    private MenuItem read_txt;

    @FXML
    private MenuItem read_bin;

    @FXML
    private MenuItem save_txt;

    @FXML
    private MenuItem read_xml;

    @FXML
    private MenuItem save_xml;

    @FXML
    private MenuItem save_xml2;

    @FXML
    private MenuItem save_bin;

    @FXML
    private Button btn_change_name;

    @FXML
    private ChoiceBox<String> choose_dep;

    @FXML
    private TextField txt_skill;

    @FXML
    private Button btn_xml_to_xml2;

    @FXML
    private TextField txt_id;

    @FXML
    private TableColumn<?, ?> salary;

    @FXML
    private TableColumn<Employee, Integer> number;

    @FXML
    private Button btn_change;

    @FXML
    private TableColumn<?, ?> surname;

    @FXML
    private TableColumn<Employee, Double> skill;

    @FXML
    private TextField txt_age;

    @FXML
    private TableColumn<?, ?> department;

    @FXML
    private Button btn_remove;

    @FXML
    private TableView<Employee> table;

    @FXML
    private TextField txt_surname;

    @FXML
    private Text label;

    @FXML
    private TextField txt_name;

    @FXML
    private Button btn_add;

    @FXML
    private TextField txt_rest;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<Employee, IdCard> coefficient;

    @FXML
    private TableColumn<?, ?> age;

    private Director director = new Director();
    private ObservableList<Employee> empl_obs = FXCollections.observableArrayList();
    private int index;
    private Logo logo = new LogoCreatorProxy();

    private void createRestaurant() {
        List<Employee> employees_tmp = new LinkedList<>();
        Employee manager = new Manager("Bob", "Jackson", 34, 1245, 0.12);
        Employee officiant1 = new Officiant("Mary", "Jackson", 20, 2128, 0.4);
        Employee officiant2 = new Officiant("Jon", "Paul", 18, 2129, 0.48);
        Employee officiant3 = new Officiant("Michele", "Parker", 20, 2126, 0.89);
        Employee officiant4 = new Officiant("Kate", "Voss", 20, 2124, 0.7);
        Employee chef = new Chef("Gordon", "Hill", 40, 7774, 1);
        Employee janitor1 = new Janitor("Alex", "Tom", 28, 3331, 0.5);
        Employee janitor2 = new Janitor("Kyle", "Brown", 24, 3348, 0.5);
        Employee bartender = new Bartender("Hanna", "Smith", 30, 1111, 0.74);

        employees_tmp.add(manager);
        employees_tmp.add(officiant1);
        employees_tmp.add(officiant2);
        employees_tmp.add(officiant3);
        employees_tmp.add(officiant4);
        employees_tmp.add(chef);
        employees_tmp.add(janitor1);
        employees_tmp.add(janitor2);
        employees_tmp.add(bartender);

        director.makeChanges("change employees list", null, 0, "", employees_tmp);
        director.makeChanges("change restaurant name", null, 0, "Restaurant", null);

        empl_obs = FXCollections.observableArrayList(RestaurantSingleton.getInstance().getEmployees());
    }

    private void makeList() {
        table.getSelectionModel().clearSelection();
        empl_obs = FXCollections.observableArrayList(RestaurantSingleton.getInstance().getEmployees());
    }

    private void remove() {
        if (director.getEmployees().indexOf(table.getSelectionModel().getSelectedItem()) != -1) {
            director.getEmployees().remove(table.getSelectionModel().getSelectedItem());
        }
        makeList();
    }

    private void setTable() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        number.setCellValueFactory(employee -> new SimpleObjectProperty<>(employee.getValue().getIdCard().getNumber()));
        skill.setCellValueFactory(employee -> new SimpleObjectProperty<>(employee.getValue().getIdCard().getSkill()));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        coefficient.setCellValueFactory(new PropertyValueFactory<>("coefficient"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        table.setItems(empl_obs);
    }

    private boolean checkField(TextField textField) {
        if (textField.getText().length() == 0) {
            txt_error.setText("Input name/surname!");
            return false;
        }
        return true;
    }

    private void add() {
        int selectedIndex = choose_dep.getSelectionModel().getSelectedIndex();
        if (checkDigit(txt_id)) {
            int id = Integer.parseInt(txt_id.getText());
            if (selectedIndex == -1) {
                txt_error.setText("Choose department!");
            } else {
                if (checkDigit(txt_age) && checkField(txt_name) && checkField(txt_surname)
                        && checkID(id) && checkDouble(txt_skill)) {
                    Employee employee = null;
                    switch (selectedIndex) {
                        case (0):
                            employee = new Manager(txt_name.getText(), txt_surname.getText(),
                                    Integer.parseInt(txt_age.getText()), Integer.parseInt(txt_id.getText()), Double.parseDouble(txt_skill.getText()));
                            break;
                        case (1):
                            employee = new Officiant(txt_name.getText(), txt_surname.getText(),
                                    Integer.parseInt(txt_age.getText()), Integer.parseInt(txt_id.getText()), Double.parseDouble(txt_skill.getText()));
                            break;
                        case (2):
                            employee = new Janitor(txt_name.getText(), txt_surname.getText(),
                                    Integer.parseInt(txt_age.getText()), Integer.parseInt(txt_id.getText()), Double.parseDouble(txt_skill.getText()));
                            break;
                        case (3):
                            employee = new Chef(txt_name.getText(), txt_surname.getText(),
                                    Integer.parseInt(txt_age.getText()), Integer.parseInt(txt_id.getText()), Double.parseDouble(txt_skill.getText()));
                            break;
                        case (4):
                            employee = new Bartender(txt_name.getText(), txt_surname.getText(),
                                    Integer.parseInt(txt_age.getText()), Integer.parseInt(txt_id.getText()), Double.parseDouble(txt_skill.getText()));
                            break;
                    }
                    choose_dep.getSelectionModel().clearSelection();
                    director.makeChanges("add employee", employee, 0, "", null);
                    txt_error.setText("");
                    makeList();
                    clear();
                }
            }
        }
    }

    private void select() {
        Employee employeeToChange = table.getSelectionModel().getSelectedItem();
        if (employeeToChange == null)
            return;
        index = RestaurantSingleton.getInstance().getEmployees().indexOf(employeeToChange);
        txt_name.setText(employeeToChange.getName());
        txt_surname.setText(employeeToChange.getSurname());
        txt_age.setText(String.valueOf(employeeToChange.getAge()));
        txt_id.setText(String.valueOf(employeeToChange.getIdCard().getNumber()));
        txt_skill.setText(String.valueOf(employeeToChange.getIdCard().getSkill()));
    }

    private void change() {
        if (checkDigit(txt_id)) {
            int id = Integer.parseInt(txt_id.getText());
            if (checkDigit(txt_age) || checkField(txt_name) || checkField(txt_surname)
                    || checkID(id) || checkDouble(txt_skill)) {
                txt_error.setText("");
                Employee employee = director.getEmployees().get(index);
                employee.setName(txt_name.getText());
                employee.setSurname(txt_surname.getText());
                employee.setIdCard(
                        new IdCard(Integer.parseInt(txt_id.getText()), Double.parseDouble(txt_skill.getText())));
                director.makeChanges("change employee data", employee, index, "", null);
                makeList();
                clear();
                table.refresh();
            }
        }
    }

    private void clear() {
        txt_name.clear();
        txt_surname.clear();
        txt_age.clear();
        txt_id.clear();
        txt_skill.clear();
        txt_rest.clear();
    }

    private void changeRestName() {
        if (checkField(txt_rest)) {
            director.makeChanges("change restaurant name", null, 0, txt_rest.getText(), null);
            label.setText(director.getName());
            clear();
        }
    }

    private void serializeBinary() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Document");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary files", "*.bin");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(primaryStage);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(director.getEmployees());
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void deserializeBinary() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Document");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary files", "*.bin");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            director.makeChanges("change employees list", null, 0, "", (List<Employee>) ois.readObject());
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        makeList();
        setTable();
        table.refresh();
    }

    private void serializeXML() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        FileOutputStream os = new FileOutputStream(file);
        XMLEncoder encoder = new XMLEncoder(os);
        encoder.writeObject(director.getEmployees());
        encoder.close();
    }

    private void serializeXML2() throws Exception {
        Serializer serializer = new Persister();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        FileOutputStream os = new FileOutputStream(file);
        for (int i = 0; i < director.getEmployees().size(); i++) {
            serializer.write(director.getEmployees().get(i), os);
        }
        os.close();
    }

    private void deserializeXML() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Document");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);
            FileInputStream fis = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(fis);
            director.makeChanges("change employees list", null, 0, "", (List<Employee>) decoder.readObject());
            decoder.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        makeList();
        setTable();
        table.refresh();
    }

    private boolean checkID(int id) {
        for (Employee employee : RestaurantSingleton.getInstance().getEmployees()) {
            if (employee.getIdCard().getNumber() == id) {
                txt_error.setText("ID already exists!");
                return false;
            }
        }
        txt_error.setText("");
        return true;
    }

    private boolean checkDigit(TextField text) {
        try {
            Integer.parseInt(text.getText());
            txt_error.setText("");
            return true;
        } catch (final NumberFormatException e) {
            txt_error.setText("Check age/ID input!");
            return false;
        }
    }

    private boolean checkDouble(TextField text) {
        try {
            Double.parseDouble(text.getText());
            txt_error.setText("");
            return true;
        } catch (final NumberFormatException e) {
            txt_error.setText("Check skill input!");
            return false;
        }
    }

    private void serializeJson() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        FileWriter writer = new FileWriter(file);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        writer.write(gson.toJson(director.getEmployees()));
        writer.close();
    }

    private void deserializeJson() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON or XML files", "*.json", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        String[] name = file.getName().split("\\.");
        String ext = name[name.length - 1];
        if (ext.equals("xml")) {
            FileInputStream fis = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(fis);
            director.makeChanges("change employees list", null, 0, "", (List<Employee>) decoder.readObject());
            decoder.close();
            fis.close();
        } else {
            Type listType = new TypeToken<List<Employee>>() {
            }.getType();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Employee.class, new JsonDeserializerWithInheritance<Employee>());
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String listGson = bufferedReader.readLine();
            director.makeChanges("change employees list", null, 0, "", gson.fromJson(listGson, listType));
        }
        makeList();
        setTable();
        table.refresh();
    }

    private void chooseLogo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        Image image = new Image(file.toURI().toString());
        logo.change(image);
        setImg_logo(logo.getImage());
    }

    @FXML
    void initialize() {
        createRestaurant();
        table.setOnMouseClicked(event -> select());
        choose_dep.getItems().add("manager");
        choose_dep.getItems().add("officiant");
        choose_dep.getItems().add("janitor");
        choose_dep.getItems().add("chef");
        choose_dep.getItems().add("bartender");
        label.setText(director.getName());
        setTable();
        btn_remove.setOnAction(event -> {
            remove();
            setTable();
        });
        btn_add.setOnAction(event -> {
            add();
            setTable();
        });
        btn_change.setOnAction(event -> {
            change();
            setTable();
        });
        btn_change_name.setOnAction(event -> changeRestName());
        save_bin.setOnAction(event -> serializeBinary());
        save_xml.setOnAction(event -> {
            try {
                serializeXML();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        save_xml2.setOnAction(event -> {
            try {
                serializeXML2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        save_txt.setOnAction(event -> {
            try {
                serializeJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        read_bin.setOnAction(event -> {
            deserializeBinary();
            table.refresh();
        });
        read_xml.setOnAction(event -> {
            deserializeXML();
            table.refresh();
        });
        read_txt.setOnAction(event -> {
            try {
                deserializeJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
            table.refresh();
        });
        btn_view_logo.setOnAction(event -> {
            setImg_logo(logo.getImage());
        });
        btn_change_logo.setOnAction(event -> {
            chooseLogo();
        });
    }
}
