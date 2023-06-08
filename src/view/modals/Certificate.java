package view.modals;

import interfaces.Visibled;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

public class Certificate implements Printable, Visibled {
    private JFrame jFrame;
    private static JPanel jPanel;
    private Defense defense;

    public Certificate(Defense defense) {
        this.defense = defense;
        initialize();
    }

    private void initialize() {
        jFrame = new JFrame("Certificate");
        jFrame.setMinimumSize(new Dimension(800, 1000));
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        jPanel = new JPanel(new BorderLayout());
        jPanel.setBackground(Color.WHITE);

        JPanel jPanel1 = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        ImageIcon logoIcon = new ImageIcon(ConstatesPath.PATH + "/src/imgs/iconUfam.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setOpaque(false);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // Insert the university name
        JLabel universityLabel = new JLabel("Federal University of Amazonas");
        universityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        universityLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        universityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                        .addComponent(universityLabel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()

                        .addComponent(logoLabel)
                        .addComponent(universityLabel)

        );

        jPanel.add(jPanel1, BorderLayout.NORTH);

        Student student = defense.getStudentDefending();

        JLabel contentLabel = new JLabel("<html><body style='width: 300px;'>The Institute of Computing at the Federal University of Amazonas certifies for all legal purposes that<br><br>" +
                student.getName() +"<br>holder of CPF "+ student.getCpf() +"<br>successfully defended his article entitled<br><br>" +
                defense.getDefenseTitle() +"<br>on " + Utils.dateForExtension(defense.getDate()) + "<br>and was approved by the board with a grade of " + defense.getFinalPontuation() + ".</body></html>");
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contentLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);

        jPanel.add(contentLabel, BorderLayout.CENTER);

        jPanel.add(signaturePanel(), BorderLayout.PAGE_END); // Add the SignaturePanel

        jFrame.getContentPane().add(jPanel);
    }

    private JPanel signatureTeacher(String teacherName, String teacherDescribe){
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1,BoxLayout.Y_AXIS));

        String lineHTML = "<html><body><hr style='border-top: 1px solid black; width:300px;'></body></html>";
        String legendHTML = "<html><body><b>" + teacherName + "</b><br>" + teacherDescribe + "</body></html>";

        JLabel lineLabel = new JLabel(lineHTML);
        JLabel legendLabel = new JLabel(legendHTML);

        legendLabel.setFont(new Font("Arial", Font.PLAIN, 12));


        jPanel1.add(lineLabel);
        jPanel1.add(legendLabel);

        return jPanel1;
    }

    private JPanel createLine(ArrayList<JPanel> components){

        JPanel jPanel1 = new JPanel();
        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);

        if(components != null && components.size() >= 2){
                JPanel teache1 = components.get(0);
                components.remove(0);
                JPanel teache2 = components.get(0);
                components.remove(0);

                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(
                                        layout.createSequentialGroup()
                                                .addComponent(teache1)
                                                .addComponent(teache2)
                                )
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addGroup(
                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(teache1)
                                                .addComponent(teache2)
                                )
                                .addGap(25)
                );

        } else if (components != null && components.size() == 1) {
                JPanel teache1 = components.get(0);
                components.remove(0);


            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(
                                    layout.createSequentialGroup()
                                            .addComponent(teache1)
                            )
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(teache1)
                            )
            );
        }

        return jPanel1;

    }

    private JPanel signaturePanel(){

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));

        ArrayList<JPanel> panels = new ArrayList<>();

        Teacher teacher = defense.getTeacherAdvisor();

        panels.add(signatureTeacher(teacher.getName(), "Supervisor Professor"));

        for(Teacher teacher1: defense.getBoardOfTeachers()){
            panels.add(signatureTeacher(teacher1.getName(), "Professor of the Bank"));
        }


        JPanel jPanel2 = createLine(panels);
        JPanel jPanel3 = createLine(panels);

        ArrayList<JPanel> comps = new ArrayList<>();
        comps.add(jPanel2);
        comps.add(jPanel3);

        for(JPanel panel: comps){
            jPanel1.add(panel);
        }

        return jPanel1;
    }

    public void setVisible(boolean value) {
        jFrame.setVisible(value);
    }

    public void printed(){
        this.setVisible(true);
        PrinterJob job = PrinterJob.getPrinterJob();

        // Set the Printable object to the PrinterJob
        job.setPrintable(this);

        // Show the print dialog to the user
        if (job.printDialog()) {
            try {
                // Start the printing process
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }

        this.destroy();
    }

    private void destroy() {
        this.jFrame.dispose();
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Get the printable area
        Dimension frameSize = jFrame.getSize();
        double scaleX = pageFormat.getImageableWidth() / frameSize.getWidth();
        double scaleY = pageFormat.getImageableHeight() / frameSize.getHeight();
        double scale = Math.min(scaleX, scaleY);

        // Apply the scaling and translate to the printable area
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.scale(scale, scale);

        // Print the frame content
        jFrame.printAll(g2d);

        return PAGE_EXISTS;
    }

}
