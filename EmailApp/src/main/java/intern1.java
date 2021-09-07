import java.awt.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.Properties;

class Component extends JFrame {


    private static final long serialVersionUID = 1L;

    //DECLERATIONS OF VARIABLES:

    JLabel user, fr, pass1, to, sub, com;
    JTextField t1, t2, t3, t4, labl;
    JTextArea ar;
    JScrollPane pane;
    JButton attach, cancel, send;
    private JFileChooser chooser;
    File[] files;
    Boolean flag;

        //CONSTRUCTOR:

         Component() {

        //DECLARING ALL THE COMPONENTS FOR THE GUI:

        user = new JLabel("---COMPOSE YOUR E-MAIL---");
        user.setBounds(320, 20, 700, 100);
        user.setFont(new Font("Candara", Font.BOLD, 32));
        user.setForeground(new Color(105, 105, 105));

        fr = new JLabel("From:");
        fr.setBounds(70, 85, 100, 100);
        fr.setFont(new Font("Candara", Font.BOLD, 25));

        pass1 = new JLabel("Password:");
        pass1.setBounds(70, 140, 200, 100);
        pass1.setFont(new Font("Candara", Font.BOLD, 25));

        to = new JLabel("To:");
        to.setBounds(70, 190, 100, 100);
        to.setFont(new Font("Candara", Font.BOLD, 25));

        sub = new JLabel("Subject:");
        sub.setBounds(70, 270, 100, 100);
        sub.setFont(new Font("Candara", Font.BOLD, 25));

        com = new JLabel("-: COMPOSE :-");
        com.setBounds(400, 340, 500, 100);
        com.setFont(new Font("Candara", Font.BOLD, 32));
        com.setForeground(new Color(139, 69, 19));

        ar = new JTextArea();
        ar.setBounds(100, 430, 800, 200);
        ar.setBackground(new Color(230, 230, 250));
        ar.setForeground(Color.BLACK);
        ar.setFont(new Font("Candara", Font.BOLD, 25));
        ar.setLineWrap(true);

        labl = new JTextField("");
        labl.setBounds(100, 650, 800, 50);
        labl.setFont(new Font("Dubai", Font.BOLD, 20));
        labl.setEditable(false);

        pane = new JScrollPane(ar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBounds(100, 430, 800, 200);

        attach = new JButton("ATTACH");
        attach.setBounds(160, 750, 180, 55);
        attach.setFont(new Font("Georgia", Font.BOLD, 20));

        cancel = new JButton("CANCEL");
        cancel.setBounds(400, 750, 180, 55);
        cancel.setFont(new Font("Georgia", Font.BOLD, 20));

        send = new JButton("SEND");
        send.setBounds(640, 750, 180, 55);
        send.setFont(new Font("Georgia", Font.BOLD, 20));

        t1 = new JTextField();
        t1.setBounds(250, 120, 600, 40);
        t1.setBackground(new Color(230, 230, 250));
        t1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        t2 = new JTextField();
        t2.setBounds(250, 170, 600, 40);
        t2.setBackground(new Color(230, 230, 250));
        t2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        t3 = new JTextField();
        t3.setBounds(250, 220, 600, 40);
        t3.setBackground(new Color(230, 230, 250));
        t3.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        t4 = new JTextField();
        t4.setBounds(250, 300, 600, 40);
        t4.setBackground(new Color(230, 230, 250));
        t4.setFont(new Font("Comic Sans MS", Font.BOLD, 25));

        //ADDING CONTENTPANE TO JFRAME:

        Container c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(255, 218, 185));

        //ADDING COMPONENTS TO CONTENTPANE:

        c.add(user);
        c.add(fr);
        c.add(to);
        c.add(sub);
        c.add(com);
        c.add(labl);
        c.add(t1);
        c.add(t2);
        c.add(t3);
        c.add(t4);
        c.add(pane);
        c.add(pass1);
        c.add(attach);
        c.add(cancel);
        c.add(send);

        //DEFINING ATTACH BUTTON FUNCTIONALITIES:

        attach.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                chooser = new JFileChooser("C:\\Users\\HP\\Desktop");
                chooser.setMultiSelectionEnabled(true);
                int a = chooser.showDialog(null, "OPEN");
                if (a == JFileChooser.APPROVE_OPTION) {
                    flag=true;
                    files = chooser.getSelectedFiles();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < files.length; i++) {
                        sb.append(files[i] + " ");
                    }
                    String pa = sb.toString();
                    labl.setText(pa);
                } else {
                    labl.setText("");
                    flag=false;
                }
            }
        });

        //DEFINING CANCEL BUTTON FUNCTIONALITIES:

        cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                ar.setText("");
                flag = false;
                System.exit(0);

            }
        });

        //DEFINING SEND BUTTON FUNCTIONALTIES:

        send.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String host = "smtp.gmail.com";
                Properties prop = System.getProperties();
                prop.put("mail.smtp.host", host);
                prop.put("mail.smtp.port", "465");
                prop.put("mail.smtp.ssl.enable", "true");
                prop.put("mail.smtp.auth", "true");

                Session ses = Session.getInstance(prop, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(t1.getText(),t2.getText());
                    }
                });

                ses.setDebug(true);

                if(flag==false) {

                    MimeMessage mess = new MimeMessage(ses);
                    try {
                        mess.setFrom();
                        mess.addRecipient(Message.RecipientType.TO,new InternetAddress(t3.getText()));
                        mess.setSubject(t4.getText());
                        mess.setText(ar.getText());
                        Transport.send(mess);
                        JOptionPane.showMessageDialog(null, "YOUR E-MAIL IS SENT");
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        ar.setText("");
                    }
                    catch (Exception exp) {
                        exp.printStackTrace();
                        JOptionPane.showMessageDialog(null, "YOU DID A MISTAKE", "ERROR SENDING MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    MimeMessage mess = new MimeMessage(ses);
                    try {
                        mess.setFrom();
                        mess.addRecipient(Message.RecipientType.TO, new InternetAddress(t3.getText()));
                        mess.setSubject(t4.getText());
                        MimeMultipart mul = new MimeMultipart();
                        MimeBodyPart text = new MimeBodyPart();
                        mul.addBodyPart(text);
                        try {
                            text.setText(ar.getText());
                            for (int j = 0; j < files.length; j++) {
                                MimeBodyPart fi = new MimeBodyPart();
                                fi.attachFile(files[j]);
                                mul.addBodyPart(fi);
                            }
                            mess.setContent(mul);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        Transport.send(mess);
                        flag = false;
                        JOptionPane.showMessageDialog(null, "E-MAIL SENT");
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        ar.setText("");
                    }
                    catch (Exception exp) {
                        exp.printStackTrace();
                        JOptionPane.showMessageDialog(null, "YOU DID A MISTAKE", "ERROR SENDING MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }

        });
    }
}

    //MAIN CLASS:

    public class intern1 {

        public static void main(String[] args) {
        Component j = new Component();

        //APPLYING JFRAME CHARACTERISTICS:

        j.setVisible(true);
        j.setDefaultCloseOperation(Component.EXIT_ON_CLOSE);
        j.setSize(1000, 1000);
        j.setLocationRelativeTo(null);
        j.setResizable(false);

    }
}