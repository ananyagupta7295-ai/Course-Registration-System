import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewNewNewCourseRegistration extends Frame implements ActionListener {

    // Pages
    private CardLayout cardLayout;
    private Panel mainPanel, homePanel, registrationPage, historyPage;
    private Panel formPanel;

    // Form fields
    private TextField nameField, regField, fatherField, dobField;
    private TextField emailField, phoneField, addressField;
    private CheckboxGroup genderGroup;
    private Checkbox male, female, other;
    private Choice courseChoice, semesterChoice, yearChoice;

    // Buttons
    private Button homeRegisterButton, historyButton;
    private Button submitButton, updateButton, deleteButton, clearButton, formBackButton;
    private Button refreshHistoryButton, historyBackButton;
    private TextArea historyArea;

    // Database configuration
    private static final String SERVER_URL =
            "jdbc:mysql://localhost:3306/?serverTimezone=UTC";

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/course_registration?serverTimezone=UTC";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    // Colors
    private final Color navy = new Color(20, 45, 75);
    private final Color blue = new Color(30, 140, 190);
    private final Color background = new Color(240, 244, 248);
    private final Color green = new Color(40, 140, 85);
    private final Color red = new Color(190, 65, 65);
    private final Color gray = new Color(80, 90, 100);


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public NewNewNewCourseRegistration() {

        initializeDatabase();

        setTitle("Course Registration System");
        setSize(950, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        createHomePage();
        createRegistrationPage();
        createHistoryPage();

        mainPanel.add(homePanel, "HOME");
        mainPanel.add(registrationPage, "REGISTRATION");
        mainPanel.add(historyPage, "HISTORY");

        add(mainPanel, BorderLayout.CENTER);

        cardLayout.show(mainPanel, "HOME");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }


    // =====================================================
    // HOME PAGE
    // =====================================================

    private void createHomePage() {

        homePanel = new Panel(null);
        homePanel.setBackground(background);

        Panel header = new Panel(null);
        header.setBackground(navy);
        header.setBounds(0, 0, 950, 155);

        Label title = new Label(
                "COURSE REGISTRATION SYSTEM",
                Label.CENTER
        );

        title.setBounds(100, 30, 750, 45);
        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setForeground(Color.WHITE);
        title.setBackground(navy);

        Label subtitle = new Label(
                "Student Course Registration Portal",
                Label.CENTER
        );

        subtitle.setBounds(100, 82, 750, 28);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(190, 220, 235));
        subtitle.setBackground(navy);

        header.add(title);
        header.add(subtitle);

        homePanel.add(header);

        Label welcome = new Label(
                "Welcome to the Course Registration Portal",
                Label.CENTER
        );

        welcome.setBounds(125, 235, 700, 45);
        welcome.setFont(new Font("Arial", Font.BOLD, 23));
        welcome.setForeground(navy);

        homePanel.add(welcome);

        Label info1 = new Label(
                "Register your personal, contact and academic details.",
                Label.CENTER
        );

        info1.setBounds(125, 305, 700, 30);
        info1.setFont(new Font("Arial", Font.PLAIN, 16));

        homePanel.add(info1);

        Label info2 = new Label(
                "You can also view previously submitted registrations.",
                Label.CENTER
        );

        info2.setBounds(125, 340, 700, 30);
        info2.setFont(new Font("Arial", Font.PLAIN, 16));

        homePanel.add(info2);


        // CENTRE REGISTER BUTTON

        homeRegisterButton = makeButton("REGISTER", blue);

        homeRegisterButton.setBounds(285, 425, 170, 55);

        homeRegisterButton.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        homeRegisterButton.addActionListener(this);

        homePanel.add(homeRegisterButton);


        // HISTORY BUTTON

        historyButton = makeButton("HISTORY", navy);

        historyButton.setBounds(495, 425, 170, 55);

        historyButton.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        historyButton.addActionListener(this);

        homePanel.add(historyButton);
    }


    // =====================================================
    // REGISTRATION PAGE
    // =====================================================

    private void createRegistrationPage() {

        registrationPage = new Panel(new BorderLayout());

        registrationPage.setBackground(background);

        registrationPage.add(
                createHeader(
                        "STUDENT REGISTRATION",
                        "Enter the student's details below"
                ),
                BorderLayout.NORTH
        );

        formPanel = new Panel(null);

        formPanel.setBackground(background);

        formPanel.setPreferredSize(
                new Dimension(900, 850)
        );

        Font labelFont = new Font(
                "Arial",
                Font.PLAIN,
                15
        );

        Font headingFont = new Font(
                "Arial",
                Font.BOLD,
                18
        );


        // PERSONAL INFORMATION

        addSectionHeading(
                "PERSONAL INFORMATION",
                35,
                headingFont
        );

        addFormLabel("Student Name", 85, labelFont);

        nameField = addTextField(85);

        addFormLabel("Registration Number", 130, labelFont);

        regField = addTextField(130);

        addFormLabel("Father's / Guardian Name", 175, labelFont);

        fatherField = addTextField(175);

        addFormLabel("Date of Birth", 220, labelFont);

        dobField = addTextField(220);

        addFormLabel("Gender", 265, labelFont);


        // Gender Selection

        genderGroup = new CheckboxGroup();

        male = new Checkbox("Male", genderGroup, true);

        female = new Checkbox("Female", genderGroup, false);

        other = new Checkbox("Other", genderGroup, false);

        male.setBounds(350, 265, 90, 30);

        female.setBounds(455, 265, 100, 30);

        other.setBounds(570, 265, 90, 30);

        male.setBackground(background);

        female.setBackground(background);

        other.setBackground(background);

        formPanel.add(male);

        formPanel.add(female);

        formPanel.add(other);


        // CONTACT INFORMATION

        addSectionHeading(
                "CONTACT INFORMATION",
                325,
                headingFont
        );

        addFormLabel("Email Address", 375, labelFont);

        emailField = addTextField(375);

        addFormLabel("Phone Number", 420, labelFont);

        phoneField = addTextField(420);

        addFormLabel("Address", 465, labelFont);

        addressField = addTextField(465);


        // ACADEMIC INFORMATION

        addSectionHeading(
                "ACADEMIC INFORMATION",
                530,
                headingFont
        );

        addFormLabel("Course", 580, labelFont);

        courseChoice = new Choice();

        courseChoice.add("B.Tech");
        courseChoice.add("BCA");
        courseChoice.add("MCA");
        courseChoice.add("M.Tech");

        courseChoice.setBounds(350, 580, 350, 30);

        formPanel.add(courseChoice);


        // Semester

        addFormLabel("Semester", 625, labelFont);

        semesterChoice = new Choice();

        for (int i = 1; i <= 8; i++) {
            semesterChoice.add("Semester " + i);
        }

        semesterChoice.setBounds(350, 625, 350, 30);

        formPanel.add(semesterChoice);


        // Academic Year

        addFormLabel("Academic Year", 670, labelFont);

        yearChoice = new Choice();

        yearChoice.add("1st Year");
        yearChoice.add("2nd Year");
        yearChoice.add("3rd Year");
        yearChoice.add("4th Year");

        yearChoice.setBounds(350, 670, 350, 30);

        formPanel.add(yearChoice);


        // BUTTONS

        submitButton = makeButton("SUBMIT", green);

        updateButton = makeButton("UPDATE", blue);

        deleteButton = makeButton("DELETE", red);

        clearButton = makeButton("CLEAR", gray);

        formBackButton = makeButton("BACK", navy);

        Button[] buttons = {
                submitButton,
                updateButton,
                deleteButton,
                clearButton,
                formBackButton
        };

        int x = 45;

        for (Button b : buttons) {

            b.setBounds(x, 745, 145, 45);

            b.addActionListener(this);

            formPanel.add(b);

            x += 165;
        }


        // SCROLL PANE

        ScrollPane scrollPane = new ScrollPane(
                ScrollPane.SCROLLBARS_AS_NEEDED
        );

        scrollPane.add(formPanel);

        // Increased vertical scrolling speed

        scrollPane.getVAdjustable().setUnitIncrement(35);

        scrollPane.getVAdjustable().setBlockIncrement(180);

        registrationPage.add(
                scrollPane,
                BorderLayout.CENTER
        );
    }


    // =====================================================
    // FORM HELPER METHODS
    // =====================================================

    private void addSectionHeading(
            String text,
            int y,
            Font font
    ) {

        Label heading = new Label(text);

        heading.setBounds(80, y, 400, 30);

        heading.setFont(font);

        heading.setForeground(blue);

        formPanel.add(heading);
    }


    private void addFormLabel(
            String text,
            int y,
            Font font
    ) {

        Label label = new Label(text);

        label.setBounds(80, y, 240, 30);

        label.setFont(font);

        formPanel.add(label);
    }


    private TextField addTextField(int y) {

        TextField field = new TextField();

        field.setBounds(350, y, 350, 30);

        formPanel.add(field);

        return field;
    }


    // =====================================================
    // HISTORY PAGE
    // =====================================================

    private void createHistoryPage() {

        historyPage = new Panel(new BorderLayout());

        historyPage.setBackground(background);

        historyPage.add(
                createHeader(
                        "REGISTRATION HISTORY",
                        "Previously submitted student records"
                ),
                BorderLayout.NORTH
        );


        historyArea = new TextArea(
                "Click REFRESH to load registration records.",
                25,
                100,
                TextArea.SCROLLBARS_BOTH
        );

        historyArea.setEditable(false);

        historyArea.setFont(
                new Font("Monospaced", Font.PLAIN, 13)
        );

        historyPage.add(
                historyArea,
                BorderLayout.CENTER
        );


        Panel bottom = new Panel(
                new FlowLayout(FlowLayout.CENTER, 25, 12)
        );

        refreshHistoryButton = makeButton(
                "REFRESH HISTORY",
                blue
        );

        historyBackButton = makeButton(
                "BACK TO HOME",
                navy
        );

        refreshHistoryButton.addActionListener(this);

        historyBackButton.addActionListener(this);

        bottom.add(refreshHistoryButton);

        bottom.add(historyBackButton);

        historyPage.add(
                bottom,
                BorderLayout.SOUTH
        );
    }


    // =====================================================
    // HEADER DESIGN
    // =====================================================

    private Panel createHeader(
            String titleText,
            String subtitleText
    ) {

        Panel header = new Panel(
                new GridLayout(2, 1)
        );

        header.setBackground(navy);

        Label title = new Label(
                titleText,
                Label.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 23)
        );

        title.setForeground(Color.WHITE);

        title.setBackground(navy);

        Label subtitle = new Label(
                subtitleText,
                Label.CENTER
        );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        subtitle.setForeground(
                new Color(190, 220, 235)
        );

        subtitle.setBackground(navy);

        header.add(title);

        header.add(subtitle);

        header.setPreferredSize(
                new Dimension(950, 95)
        );

        return header;
    }


    // =====================================================
    // BUTTON DESIGN
    // =====================================================

    private Button makeButton(
            String text,
            Color color
    ) {

        Button button = new Button(text);

        button.setBackground(color);

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        return button;
    }


    // =====================================================
    // DATABASE CONNECTION
    // =====================================================

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                DB_URL,
                DB_USER,
                DB_PASSWORD
        );
    }


    // =====================================================
    // CREATE DATABASE AND TABLE
    // =====================================================

    private void initializeDatabase() {

        String createDatabase =
                "CREATE DATABASE IF NOT EXISTS course_registration";

        String createTable =
                "CREATE TABLE IF NOT EXISTS students ("
                        + "reg_no VARCHAR(30) PRIMARY KEY, "
                        + "student_name VARCHAR(100) NOT NULL, "
                        + "father_name VARCHAR(100), "
                        + "dob VARCHAR(20), "
                        + "gender VARCHAR(10), "
                        + "email VARCHAR(100), "
                        + "phone VARCHAR(20), "
                        + "address VARCHAR(255), "
                        + "course VARCHAR(50), "
                        + "semester VARCHAR(20), "
                        + "academic_year VARCHAR(20))";


        try (
                Connection con = DriverManager.getConnection(
                        SERVER_URL,
                        DB_USER,
                        DB_PASSWORD
                );

                Statement st = con.createStatement()
        ) {

            st.executeUpdate(createDatabase);

        } catch (SQLException e) {

            System.err.println("Database creation/connection error:");

            e.printStackTrace();

            return;
        }


        try (
                Connection con = getConnection();

                Statement st = con.createStatement()
        ) {

            st.executeUpdate(createTable);

        } catch (SQLException e) {

            System.err.println("Table creation error:");

            e.printStackTrace();
        }
    }


    // =====================================================
    // GET SELECTED GENDER
    // =====================================================

    private String selectedGender() {

        Checkbox selected =
                genderGroup.getSelectedCheckbox();

        return selected == null
                ? "Not set"
                : selected.getLabel();
    }


    // =====================================================
    // VALIDATE FORM
    // =====================================================

    private boolean validateRequiredFields() {

        if (
                nameField.getText().trim().isEmpty()
                        || regField.getText().trim().isEmpty()
        ) {

            showMessage(
                    "Please enter Student Name and Registration Number."
            );

            return false;
        }

        return true;
    }


    // =====================================================
    // INSERT STUDENT DETAILS
    // =====================================================

    private void submitStudent() {

        if (!validateRequiredFields()) {
            return;
        }

        String sql =
                "INSERT INTO students "
                        + "(reg_no, student_name, father_name, dob, gender, "
                        + "email, phone, address, course, semester, academic_year) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            fillStatementForInsert(ps);

            ps.executeUpdate();

            showMessage(
                    "Registration saved successfully!"
            );

            clearForm();

        } catch (SQLIntegrityConstraintViolationException e) {

            showMessage(
                    "This Registration Number already exists. Use UPDATE to change the record."
            );

        } catch (SQLException e) {

            showDatabaseError(
                    "Unable to save registration",
                    e
            );
        }
    }


    // =====================================================
    // FILL INSERT QUERY
    // =====================================================

    private void fillStatementForInsert(
            PreparedStatement ps
    ) throws SQLException {

        ps.setString(1, regField.getText().trim());

        ps.setString(2, nameField.getText().trim());

        ps.setString(3, fatherField.getText().trim());

        ps.setString(4, dobField.getText().trim());

        ps.setString(5, selectedGender());

        ps.setString(6, emailField.getText().trim());

        ps.setString(7, phoneField.getText().trim());

        ps.setString(8, addressField.getText().trim());

        ps.setString(9, courseChoice.getSelectedItem());

        ps.setString(10, semesterChoice.getSelectedItem());

        ps.setString(11, yearChoice.getSelectedItem());
    }


    // =====================================================
    // UPDATE STUDENT DETAILS
    // =====================================================

    private void updateStudent() {

        if (regField.getText().trim().isEmpty()) {

            showMessage(
                    "Enter the Registration Number of the student to update."
            );

            return;
        }

        String sql =
                "UPDATE students SET "
                        + "student_name=?, father_name=?, dob=?, gender=?, "
                        + "email=?, phone=?, address=?, course=?, semester=?, academic_year=? "
                        + "WHERE reg_no=?";


        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, nameField.getText().trim());

            ps.setString(2, fatherField.getText().trim());

            ps.setString(3, dobField.getText().trim());

            ps.setString(4, selectedGender());

            ps.setString(5, emailField.getText().trim());

            ps.setString(6, phoneField.getText().trim());

            ps.setString(7, addressField.getText().trim());

            ps.setString(8, courseChoice.getSelectedItem());

            ps.setString(9, semesterChoice.getSelectedItem());

            ps.setString(10, yearChoice.getSelectedItem());

            ps.setString(11, regField.getText().trim());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                showMessage(
                        "Registration updated successfully!"
                );

            } else {

                showMessage(
                        "No record found with this Registration Number."
                );
            }

        } catch (SQLException e) {

            showDatabaseError(
                    "Unable to update registration",
                    e
            );
        }
    }


    // =====================================================
    // DELETE STUDENT DETAILS
    // =====================================================

    private void deleteStudent() {

        if (regField.getText().trim().isEmpty()) {

            showMessage(
                    "Enter the Registration Number of the student to delete."
            );

            return;
        }

        String sql =
                "DELETE FROM students WHERE reg_no=?";


        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    regField.getText().trim()
            );

            int rows = ps.executeUpdate();

            if (rows > 0) {

                clearForm();

                showMessage(
                        "Registration deleted successfully!"
                );

            } else {

                showMessage(
                        "No record found with this Registration Number."
                );
            }

        } catch (SQLException e) {

            showDatabaseError(
                    "Unable to delete registration",
                    e
            );
        }
    }


    // =====================================================
    // LOAD REGISTRATION HISTORY
    // =====================================================

    private void loadHistory() {

        String sql =
                "SELECT reg_no, student_name, father_name, dob, gender, "
                        + "email, phone, address, course, semester, academic_year "
                        + "FROM students ORDER BY student_name";


        StringBuilder result = new StringBuilder();

        result.append(
                String.format(
                        "%-15s %-22s %-20s %-12s %-10s %-25s %-15s %-25s %-12s %-12s %-15s%n",
                        "REG NO",
                        "STUDENT NAME",
                        "FATHER NAME",
                        "DOB",
                        "GENDER",
                        "EMAIL",
                        "PHONE",
                        "ADDRESS",
                        "COURSE",
                        "SEMESTER",
                        "YEAR"
                )
        );

        result.append(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
        );

        int count = 0;


        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                count++;

                result.append(
                        String.format(
                                "%-15s %-22s %-20s %-12s %-10s %-25s %-15s %-25s %-12s %-12s %-15s%n",

                                safe(rs.getString("reg_no")),

                                safe(rs.getString("student_name")),

                                safe(rs.getString("father_name")),

                                safe(rs.getString("dob")),

                                safe(rs.getString("gender")),

                                safe(rs.getString("email")),

                                safe(rs.getString("phone")),

                                safe(rs.getString("address")),

                                safe(rs.getString("course")),

                                safe(rs.getString("semester")),

                                safe(rs.getString("academic_year"))
                        )
                );
            }


            if (count == 0) {

                result.append(
                        "No registrations found in the database.\n"
                );

            } else {

                result.append(
                        "\nTotal registrations: "
                ).append(count);
            }

            historyArea.setText(result.toString());

            historyArea.setCaretPosition(0);

        } catch (SQLException e) {

            historyArea.setText(
                    "Could not load registration history.\n"
                            + e.getMessage()
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // HANDLE NULL AND LONG VALUES
    // =====================================================

    private String safe(String value) {

        if (value == null) {
            return "";
        }

        value = value.replace('\n', ' ').replace('\r', ' ');

        return value.length() > 24
                ? value.substring(0, 21) + "..."
                : value;
    }


    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        nameField.setText("");

        regField.setText("");

        fatherField.setText("");

        dobField.setText("");

        emailField.setText("");

        phoneField.setText("");

        addressField.setText("");

        genderGroup.setSelectedCheckbox(male);

        courseChoice.select(0);

        semesterChoice.select(0);

        yearChoice.select(0);
    }


    // =====================================================
    // MESSAGE DIALOG
    // =====================================================

    private void showMessage(String message) {

        final Dialog dialog = new Dialog(
                this,
                "Course Registration",
                true
        );

        dialog.setLayout(
                new BorderLayout(10, 10)
        );

        dialog.add(
                new Label(message, Label.CENTER),
                BorderLayout.CENTER
        );

        Button ok = new Button("OK");

        ok.addActionListener(
                e -> dialog.dispose()
        );

        Panel p = new Panel();

        p.add(ok);

        dialog.add(
                p,
                BorderLayout.SOUTH
        );

        dialog.setSize(430, 160);

        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);
    }


    // =====================================================
    // DATABASE ERROR
    // =====================================================

    private void showDatabaseError(
            String message,
            SQLException e
    ) {

        e.printStackTrace();

        showMessage(
                message + ": " + e.getMessage()
        );
    }


    // =====================================================
    // BUTTON ACTIONS
    // =====================================================

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();


        // REGISTER BUTTON

        if (source == homeRegisterButton) {

            cardLayout.show(
                    mainPanel,
                    "REGISTRATION"
            );
        }


        // HISTORY BUTTON

        else if (source == historyButton) {

            cardLayout.show(
                    mainPanel,
                    "HISTORY"
            );

            loadHistory();
        }


        // SUBMIT

        else if (source == submitButton) {

            submitStudent();
        }


        // UPDATE

        else if (source == updateButton) {

            updateStudent();
        }


        // DELETE

        else if (source == deleteButton) {

            deleteStudent();
        }


        // CLEAR

        else if (source == clearButton) {

            clearForm();
        }


        // BACK BUTTONS

        else if (
                source == formBackButton
                        || source == historyBackButton
        ) {

            cardLayout.show(
                    mainPanel,
                    "HOME"
            );
        }


        // REFRESH HISTORY

        else if (source == refreshHistoryButton) {

            loadHistory();
        }
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        EventQueue.invokeLater(
                NewNewNewCourseRegistration::new
        );
    }
}