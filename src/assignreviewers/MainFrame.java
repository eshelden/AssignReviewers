/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignreviewers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Eric Shelden
 */

public class MainFrame extends javax.swing.JFrame {
    File saveDir = null;
    String[] fileNames;
    boolean useTimeFile = false;
    boolean validFilesFound = false;
    boolean timeCheckBoxChecked = false;
    boolean useConflicts = true;
    boolean canRun = false;
    ArrayList mApplicants = null;
    ArrayList tReviewers = null;
    ArrayList tConflicts = null;
    Reviewer mReviewers[] = null;
    Conflict mConflicts[] = null;
    int nReviews = 0;
    int nConflicts = 0;
    int maxReviews = 10;
    int nTries = 25;
    String mAssignments[][];

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        nReviewersTextField.setText("3");
        triesText.setText("25");
        maxReviewsText.setText("10");
        InitVariables();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GetDir = new javax.swing.JButton();
        DirTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        RunButton = new javax.swing.JButton();
        nReviewersTextField = new javax.swing.JTextField();
        triesText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        conflictsCheckBox = new javax.swing.JCheckBox();
        HelpButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        maxReviewsText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        GetDir.setText("Source dir");
        GetDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetDirActionPerformed(evt);
            }
        });

        DirTextField.setEditable(false);

        jLabel1.setText("Reviews/application");

        jLabel2.setText("Tries");

        RunButton.setText("Run");
        RunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunButtonActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        conflictsCheckBox.setText("No Conflicts");
        conflictsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conflictsCheckBoxActionPerformed(evt);
            }
        });

        HelpButton.setText("Manual");
        HelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("MaxReviews/Reviewer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GetDir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nReviewersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(triesText, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maxReviewsText, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(conflictsCheckBox))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 51, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RunButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HelpButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GetDir)
                    .addComponent(DirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(nReviewersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(conflictsCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(triesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(maxReviewsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RunButton)
                    .addComponent(HelpButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

public class Reviewer {
  public  String name;
  public  int max;
  public int nAssignments;

  public Reviewer (String name, int max, int nAssignments) {
    this.name = name;
    this.max = max;
    this.nAssignments = nAssignments;
  }
}

public class Conflict {
  public  String reviewer;
  public  String applicant;

  public Conflict (String name1, String name2) {
    this.reviewer = name1;
    this.applicant = name2;
  }
}

private void InitVariables(){
     useTimeFile = false;
     validFilesFound = false;
     timeCheckBoxChecked = false;
     useConflicts = true;
     canRun = false;
     mApplicants = null;
     tReviewers = null;
     tConflicts = null;
     mReviewers = null;
     mConflicts = null;
     nReviews = 0;
     nConflicts = 0;
}
        
    private void GetDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetDirActionPerformed
        // TODO add your handling code here:
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new java.io.File("."));
            fileChooser.setDialogTitle("Specify where to find input files."); 
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
            saveDir = fileChooser.getSelectedFile();
            InitVariables();
            File tempFile = new File(saveDir.getAbsolutePath()+"\\" + "Applicants.csv");
            boolean applicants = tempFile.exists();
            tempFile = new File(saveDir.getAbsolutePath()+"\\" + "Reviewers.csv");
            boolean reviewers = tempFile.exists();
            tempFile = new File(saveDir.getAbsolutePath()+"\\" + "Conflicts.csv");
            boolean conflictsExists = tempFile.exists();
            useConflicts = !conflictsCheckBox.isSelected();
            boolean conflictsOK = (conflictsExists | conflictsCheckBox.isSelected());
            if (applicants & reviewers & conflictsOK) {
                canRun = true;
                DirTextField.setText(saveDir.getName());
                mApplicants = GetSingleRowList(saveDir, "Applicants.csv");
                tReviewers = GetSingleRowList(saveDir, "Reviewers.csv");
                if (mApplicants.size() < 1 | tReviewers.size() < 1) {
                    canRun = false;
                    jTextArea1.setText("Applicant and reviewer files need at least one entry." + "\n");
                    jTextArea1.update(jTextArea1.getGraphics());
                    }
                if (useConflicts) {
                    tConflicts = GetSingleRowList(saveDir, "Conflicts.csv");
                    if(tConflicts.size() < 1){
                        canRun = false;
                        jTextArea1.setText("Conflicts file need at least one entry." + "\n");
                        jTextArea1.update(jTextArea1.getGraphics());                        
                        }
                    }
                }
            else {
                jTextArea1.setText("Folder missing one or more required files." + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                canRun = false;
                }
            if(canRun){
                jTextArea1.setText("Folder files are OK." + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                }
            }
    }//GEN-LAST:event_GetDirActionPerformed

    private ArrayList GetSingleRowList(File mDir, String fName){
        ArrayList mNames = new ArrayList();
        File tempFile = new File(mDir.getAbsolutePath()+"\\" + fName);
        boolean fileExists = tempFile.exists();
        if (fileExists) {
            BufferedReader reader;
            //times = new ArrayList<>();
            try {
                reader = new BufferedReader(new FileReader(tempFile));
                String line = reader.readLine();
                while (line != null) {
                    mNames.add(line);
                    // read next line
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                jTextArea1.setText("Error reading Applicants.csv file." + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
            } //end of try catch
            return(mNames);
        } else {
            return(null);
        }
    }
    
    private void RunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunButtonActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("working" + "\n");
        jTextArea1.update(jTextArea1.getGraphics());
        //ArrayList<File> files = null;
        //List<File> myList = null;
        //String outputFileName = "out.mlt";
        useConflicts = !conflictsCheckBox.isSelected();
      
        nReviews = Integer.parseInt(nReviewersTextField.getText());
        maxReviews = Integer.parseInt(maxReviewsText.getText());
        nTries = Integer.parseInt(triesText.getText());
        
        if (!canRun){
        jTextArea1.setText("Something is wrong. Try reloading directory." + "\n");
        jTextArea1.update(jTextArea1.getGraphics());
        return;
        }
        
        mReviewers = GetReviewers(tReviewers, maxReviews);
        if (mReviewers == null){
            jTextArea1.append("Error in Reviewer.csv." + "\n");
            jTextArea1.update(jTextArea1.getGraphics());
            return;
        }
        
        if (useConflicts){
            if (tConflicts != null){
                mConflicts = GetConflicts(tConflicts);
                if (mConflicts == null){
                    jTextArea1.setText("Conflicts.csv has errors." + "\n");
                    jTextArea1.update(jTextArea1.getGraphics());
                    return;
                }
            }
            else {
                jTextArea1.setText("Use conflicts is true, but Conflicts.csv is missing. Try reloading directory?" + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                return;
                }
        }
        
        canRun = CheckData(mApplicants, mReviewers, mConflicts);
        if (!canRun){
            jTextArea1.setText("Names in Conflicts.csv don't match other data." + "\n");
            jTextArea1.update(jTextArea1.getGraphics());
            return;
        }
        
        //create 2D string array for holding reviewer names for each applicant...
        int nReviewers = mReviewers.length;
        mAssignments = new String[mApplicants.size()][nReviews];
        for (int i = 0; i<mApplicants.size(); i++){
            for (int j = 0; j<nReviews; j++){
                mAssignments[i][j] = GetReviewer(mApplicants.get(i).toString(),j, nReviewers, i, nReviews);
            }
        }
        
        if (checkForNullAssignments()){
            jTextArea1.setText("Failed with current parameters. Try running again or changing parameters." + "\n");
            jTextArea1.update(jTextArea1.getGraphics());
        }
        else {
            WriteResults();
            jTextArea1.append("\nSuccess." + "\n");
            jTextArea1.update(jTextArea1.getGraphics());
        }
    }//GEN-LAST:event_RunButtonActionPerformed

    //#################################################################################
    
    private void WriteResults(){
        try {

    File file = new File(saveDir.getAbsolutePath()+"\\" + "Result.csv");
    //saveDir.getAbsolutePath()+"\\" + "Applicants.csv"
        if (file.exists())
            file.delete();
        
        file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            //bw.write(board, 0, board.length());
            StringBuilder builder = new StringBuilder();
            //write header//
            builder.append("Applicant,");
            for (int i  = 0; i< nReviews; i++){
                builder.append("Reviewer");
                if (i < nReviews - 1)
                    builder.append(",");
            }
            builder.append("\n");
            
            //write assigned reviewers//
            for(int i = 0; i < mApplicants.size(); i++)//for each row
            {
               builder.append(mApplicants.get(i).toString()).append(",");
               for(int j = 0; j < nReviews; j++)//for each column
               {
                  builder.append(mAssignments[i][j]).append("");//append to the output string
                  if(j < nReviews - 1)//if this is not the last row element
                     builder.append(",");//then add comma (if you don't like commas you can use spaces)
               }
               builder.append("\n");//append new line at the end of the row
            }
            builder.append("\n\n\n\n\n");
            
            for (int i = 0; i < mReviewers.length; i++){
                builder.append(mReviewers[i].name).append(",");
                for (int j = 0; j < mAssignments.length; j++){
                    if (ReviewerIsAssigned(mReviewers[i].name, j)){
                        builder.append(mApplicants.get(j).toString()).append(",");
                    }
                }
            builder.append("\n");
            }
            
            bw.write(builder.toString());//save the string representation of the board
            bw.close();
            jTextArea1.append(builder.toString());
            jTextArea1.update(jTextArea1.getGraphics());
        }

        } catch (IOException e) {

        }
    }
    
    private boolean ReviewerIsAssigned(String name, int index){
        for (int i = 0; i < nReviews; i++){
            if (mAssignments[index][i].toString().equalsIgnoreCase(name))
                return(true);
        }
        return(false);
    }
    
    private boolean checkForNullAssignments(){
        for (int i = 0; i<mApplicants.size(); i++){
            for (int j = 0; j<nReviews; j++){
                if (mAssignments[i][j] == null){
                    return (true);
                }
            }
        }
        return(false);
    }
    
    	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
    
    private String GetReviewer(String applicant, int whichReviewer, int nReviewers, int applicantID, int nReviews){
        int myTries = 0;
        boolean done = false;
        int tReviewerID;
        String rName;
        
        while(!done){
            tReviewerID = getRandomNumberInRange(0,nReviewers-1);
            myTries = myTries + 1;
            rName = mReviewers[tReviewerID].name;
            int tMax = Math.min(mReviewers[tReviewerID].max, maxReviews);
            if( (mReviewers[tReviewerID].nAssignments < tMax) & (NoConflicts(rName,applicant)) & 
                   (NoRepeat(rName, tReviewerID, applicantID, nReviews)) ){
               mReviewers[tReviewerID].nAssignments ++;
               return(rName); 
            }
		
            if (myTries == nTries){
                done = true;
            }
        }
        return(null);
    }
    
    private boolean NoRepeat(String reviewer, int reviewerID, int ApplicantID, int nReviews){
        String applicantName = mApplicants.get(ApplicantID).toString();
        String tReviewerName = mReviewers[reviewerID].name;
	    
        int nAssigned = GetNumberOfAssignedReviewers(ApplicantID);
        //System.out.println("NR" + " " + reviewer + " " + Integer.toString(reviewerID) + " " + Integer.toString(ApplicantID) + " " + Integer.toString(nReviews));
        if (nAssigned == 0){ // no reviews assigned yet
            return(true);
        }
        for (int i = 0; i<nAssigned; i++){
            //System.out.println("NR" + " " + mAssignments[ApplicantID][i]);
            if (mAssignments[ApplicantID][i].equalsIgnoreCase(reviewer)){
            return (false);
            }
        }
        return(true);
    }
    
    private int GetNumberOfAssignedReviewers(int ApplicantID){
        int nAssigned = 0;
        for (int i = 0; i<nReviews; i++){
            if (mAssignments[ApplicantID][i] != null)
                nAssigned ++;
        }
        return(nAssigned);
    }
    
    private boolean NoConflicts(String reviewer, String applicant){
        if (mConflicts == null | !useConflicts){
            return(true);
        }
        else {
            for (Conflict mConflict : mConflicts) {
                if (reviewer.equalsIgnoreCase(mConflict.reviewer) & applicant.equalsIgnoreCase(mConflict.applicant)) {
                    return(false);
                }
            }
        }
        return (true);
    }
    
    private Reviewer[] GetReviewers(ArrayList tReviewers, int maxReviews){
        int nReviewers = tReviewers.size();
        mReviewers = new Reviewer[nReviewers];
        for (int i = 0; i<nReviewers; i++){
            String tString = tReviewers.get(i).toString();
            if (tString.contains(",")){
                String[] parts = tString.split(",");
                if (parts.length == 2){
                    if (isInteger(parts[1])){
                        mReviewers[i] = new Reviewer(parts[0], Integer.parseInt(parts[1]), 0);
                    }
                    else {
                    jTextArea1.setText("Wrong format for reviewer file." + "\n");
                    jTextArea1.update(jTextArea1.getGraphics());
                    return(null);
                    }
                }
                else {
                    jTextArea1.setText("Format error for reviewer file." + "\n");
                    jTextArea1.update(jTextArea1.getGraphics());
                    return(null);
                }
            } // string has a comma
            else {
                mReviewers[i] = new Reviewer(tString, maxReviews, 0); //no comma, add default
            }
        }
        return (mReviewers);
    }
    
        private Conflict[] GetConflicts(ArrayList tConflicts){
        nConflicts = tConflicts.size();
        mConflicts = new Conflict[nConflicts];
        for (int i = 0; i<nConflicts; i++){
            String tString = tConflicts.get(i).toString();
            if (tString.contains(",")){
                //String tString = tReviewers.get(i).toString();
                String[] parts = tString.split(",");
                if (parts.length > 2){
                jTextArea1.setText("Conflicts file has too many names on a line." + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                return (null);
                }
                else{
                mConflicts[i] = new Conflict(parts[0], parts[1]);  
                }
            }
            else {
                jTextArea1.setText("Conflicts file should have comma separated list of reviewers and applicants." + "\n");
                jTextArea1.update(jTextArea1.getGraphics());
                return(null);
            }
        }    
        return (mConflicts);
    }
        
    private boolean CheckData(ArrayList mApplicants, Reviewer[] mReviewers, Conflict[] mConflicts){
        //we are just checking to make sure that the names in conflicts match those in the other 
        //objects
        boolean dataOK = true;
        if (mConflicts == null) {
            return(true);
        }
        else {
            nConflicts = mConflicts.length;
            int nApplicants = mApplicants.size();
            int nReviewers = mReviewers.length;
            

            for (int i = 0; i < nConflicts; i++){
                boolean found = false;
                for (int j = 0; j<nApplicants; j++){
                    if (mApplicants.get(j).toString().equalsIgnoreCase(mConflicts[i].applicant))
                        found = true;
                    }
               if (!found) dataOK = false; 
               } // each i
            
            for (int i = 0; i < nConflicts; i++){
                boolean found = false;
                for (int j = 0; j<nReviewers; j++){
                    if (mReviewers[j].name.equalsIgnoreCase(mConflicts[i].reviewer))
                        found = true;
                    }
               if (!found) dataOK = false; 
               } // each i

        }

        return(dataOK);
    }
    
    private void HelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpButtonActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("This app assigns reviewers to applications.\n"
                +"It requires 2-3 .csv files as input in a chosen directory.\n"
                + "Minimally, a list of applicant names in Applicants.csv and\n"
                + "reviewer names in Reviewers.csv with one name per line. \n"
                + "Optionally, in the reviewer file you can include a comma and \n"
                + "a number indicating the limit for number of reviews for a sepecific\n"
                + "reviewer. If this is absent the app will use MaxReviews\n"
                + "as the default maximum.\n\n"
                + "The app also optionally accepts a .csv file listing reviewer conflicts\n"
                + "in a file Conflicts.csv, which should contain one comma separated reviewer/\n"
                + "applicant pair of names per line. A reviewer can have more than one\n"
                + "line in the case of multiple conflicts. If there are no potential conflicts\n"
                + "The No Conflicts checkbox should be checked to prevent the app from looking\n"
                + "for the conflicts file. Do not include extra spaces between or around names.\n\n"
                
                + "Operation:\n\n"
                
                +"Specify the desired number of reviews each applicant should recieve,\n"
                +"the maximum number of assignments per reviewer and the directory\n"
                +"containing the input files.\n\n"
                +"\"Tries\" specifies the number of attempts the program will make to find\n"
                +"a reviewer that matches all criteria before giving up. It is possible\n"
                +"for the program to run numerous times before succeeding with a given\n"
                +"set of constraints, but it is also possible to specify constraints that\n"
                +"can never be satisfied.\n\n"
                
                +"If the app finds a solution, it will write the output in a file called\n"
                +"Results.csv and will also put a preview of the results in the output\n"
                +"text area at the bottom of the window. You can rerun (by pressing Run)\n"
                +"to generate new solutions. NOTE: the program deletes previous results\n"
                +"files without asking.");
        jTextArea1.update(jTextArea1.getGraphics());
    }//GEN-LAST:event_HelpButtonActionPerformed

    private void conflictsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conflictsCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conflictsCheckBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    
private ArrayList<File> getRandomElement(ArrayList<File> list, int totalItems) 
    { 
        Random rand = new Random(); 
  
        // create a temporary list for storing 
        // selected element 
        ArrayList<File> newList = new ArrayList<>(); 
        for (int i = 0; i < totalItems; i++) { 
            // take a raundom index between 0 to size  
            // of given List 
            int randomIndex = rand.nextInt(list.size()); 
            // add element in temporary list 
            newList.add(list.get(randomIndex)); 
            // Remove selected element from orginal list 
            list.remove(randomIndex); 
        }
        return newList; 
    }

public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}

public static boolean isInteger(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        Integer i = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DirTextField;
    private javax.swing.JButton GetDir;
    private javax.swing.JButton HelpButton;
    private javax.swing.JButton RunButton;
    private javax.swing.JCheckBox conflictsCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField maxReviewsText;
    private javax.swing.JTextField nReviewersTextField;
    private javax.swing.JTextField triesText;
    // End of variables declaration//GEN-END:variables
}
