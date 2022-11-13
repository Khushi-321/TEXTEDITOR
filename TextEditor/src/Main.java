import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.*;
class editor extends JFrame implements ActionListener{
    //creating text area
    JTextArea t;
    // creating frame to accommodate  text area and menu bar
    JFrame f;
    editor(){
        // initialising the frame
        f= new JFrame("Text Editor");
        //setting overall theme of application
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.metallicLookandFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }
        // initialising text area
        t=new JTextArea();
        // initialising menubar
        JMenuBar m=new JMenuBar();
        // initialising file menu
        JMenu f1=new JMenu("File");

        // create individual menu items
        // for file menu
        JMenuItem m1= new JMenuItem("New");
        JMenuItem m2=new JMenuItem("Open");
        JMenuItem m3= new JMenuItem("Save");
        JMenuItem m4= new JMenuItem("Print");
        // adding action listener
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        // adding menu items to file menu
        f1.add(m1);
        f1.add(m2);
        f1.add(m3);
        f1.add(m4);
        // initialising  edit menu
        JMenu f2= new JMenu("Edit");
        // for edit menu
        JMenuItem m5= new JMenuItem("Cut");
        JMenuItem m6= new JMenuItem("Copy");
        JMenuItem m7= new JMenuItem("Paste");
        // adding action listener
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        //adding items to menu
        f2.add(m5);
        f2.add(m6);
        f2.add(m7);
        // exit
        JMenuItem c= new JMenuItem("Exit");
        c.addActionListener(this);

        // adding menus to menuBar
        m.add(f1);
        m.add(f2);
        m.add(c);
        // adding text area and menu bar to frame
        f.add(t);
        // to add menubar
        f.setJMenuBar(m);
        f.setSize(1000,1000);
        f.show();
    }
    public void actionPerformed(ActionEvent e){
        String s=e.getActionCommand();
         if(s.equals("New")) {
            t.setText("");
     }
        else if(s.equals("Open")){
            // j file chooser as a pointer it will point to directory and it will point to that location
            JFileChooser j= new JFileChooser("c");
            // f is a drive in memory
             // to invoke dialog box on the screen
             int r = j.showOpenDialog(null);
             // int r will contain status of dialog box like if it is visible or not and location selected will also be shown.
             if(r== JFileChooser.APPROVE_OPTION){
                 // file object will help us reading the file which is located at that pointer
                 File fi=new File(j.getSelectedFile().getAbsolutePath());
                 try{
                     String s1="";
                     String s2="";

                     FileReader fr= new FileReader(fi);
                     BufferedReader br=new BufferedReader(fr);

                     s2=br.readLine();
                     while((s1=br.readLine())!=null){
                         s2=s2+ "\n" + s1;
                     }
                     t.setText(s2);
                 }
                 catch(Exception et){
                    JOptionPane.showMessageDialog(f,et.getMessage());
                 }
             }
             else{
                 JOptionPane.showMessageDialog(f,"Operation Cancelled");
             }
        }
       else if(s.equals("Save")) {
             JFileChooser j = new JFileChooser("c");

             int r = j.showSaveDialog(null);

             if (r == JFileChooser.APPROVE_OPTION) {

                 File fi = new File(j.getSelectedFile().getAbsolutePath());
                 try {
                     FileWriter wr = new FileWriter(fi);
                     BufferedWriter bw = new BufferedWriter(wr);
                     bw.write(t.getText());

                     bw.flush();
                     bw.close();
                 } catch (Exception et) {
                     JOptionPane.showMessageDialog(f, et.getMessage());
                 }
             } else {
                 JOptionPane.showMessageDialog(f, "Operation Cancelled");
             }
         }
        else if(s.equals("Print")){
            try{
                t.print();
            }
            catch(Exception et){
                JOptionPane.showMessageDialog(f,et.getMessage());
            }
        }
       else if(s.equals("Cut")){
            t.cut();
        }
       else if(s.equals("Copy")){
            t.copy();
        }
        else if(s.equals("Paste")){
            t.paste();
        }
        else if(s.equals("Exit")){
        f.setVisible(false);
        }
    }
    public static void main(String args[]){
        editor e=new editor();

}
}
