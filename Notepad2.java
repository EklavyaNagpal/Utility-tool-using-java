import java.io.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
import java.io.*;

class SpellCheck {

    private Dictionary dict;
    final static String filePath = "/C:/Users/E__A__N/Desktop/java/check/words.txt";
    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    SpellCheck() {
        dict = new Dictionary();
        dict.build(filePath);

    }

    void run() throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        String input;


File file = new File("/C:/Users/E__A__N/Desktop/java/check/check.txt");
    Scanner input1 = new Scanner(file); 
 

    int count = 0;
    while (input1.hasNext()) {
      String word  = input1.next();
      System.out.println(word);
      count = count + 1;
    

    System.out.println("Word count: " + count);




        while (true) {
           

            // input = scan.nextLine();
            input=word;

            if (input.equals("")) {
                break;
            }
            else if (dict.contains(input)) {
                System.out.println("\n" + input + " is spelled correctly");
                break;
            }
        else {
                System.out.print("is not spelled correctly, ");
                System.out.println(printSuggestions(input));
                break;
            }
        }
    }
}
    String printSuggestions(String input) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> print = makeSuggestions(input);
        if (print.size() == 0) {
            return "and I have no idea what word you could mean.\n";
        }
        sb.append("perhaps you meant:\n");
        for (String s : print) {
            sb.append("\n  -" + s);
        }
        return sb.toString();
    }

    private ArrayList<String> makeSuggestions(String input) {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(charAppended(input));
        toReturn.addAll(charMissing(input));
        toReturn.addAll(charsSwapped(input));
        return toReturn;
    }

    private ArrayList<String> charAppended(String input) { 
        ArrayList<String> toReturn = new ArrayList<>();
        for (char c : alphabet) {
            String atFront = c + input;
            String atBack = input + c;
            if (dict.contains(atFront)) {
                toReturn.add(atFront);
            }
            if (dict.contains(atBack)) {
                toReturn.add(atBack);
            }
        }
        return toReturn;
    }

    private ArrayList<String> charMissing(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        int len = input.length() - 1;
        //try removing char from the front
        if (dict.contains(input.substring(1))) {
            toReturn.add(input.substring(1));
        }
        for (int i = 1; i < len; i++) {
            //try removing each char between (not including) the first and last
            String working = input.substring(0, i);
            working = working.concat(input.substring((i + 1), input.length()));
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        if (dict.contains(input.substring(0, len))) {
            toReturn.add(input.substring(0, len));
        }
        return toReturn;
    }

    private ArrayList<String> charsSwapped(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);// System.out.println("    0:" + working);
            working = working + input.charAt(i + 1);  //System.out.println("    1:" + working);
            working = working + input.charAt(i); //System.out.println("    2:" + working);
            working = working.concat(input.substring((i + 2)));//System.out.println("    FIN:" + working); 
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        return toReturn;
    }
    public class Dictionary {
        private int M = 1319; //prime number
        final private Bucket[] array;
        public Dictionary() {
            this.M = M;

            array = new Bucket[M];
            for (int i = 0; i < M; i++) {
                array[i] = new Bucket();
            }
        }

        private int hash(String key) {
            return (key.hashCode() & 0x7fffffff) % M;
        }

        //call hash() to decide which bucket to put it in, do it.
        public void add(String key) {
            array[hash(key)].put(key);
        }

        //call hash() to find what bucket it's in, get it from that bucket. 
        public boolean contains(String input) {
            input = input.toLowerCase();
            return array[hash(input)].get(input);
        }

        public void build(String filePath) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    add(line);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
        //this method is used in my unit tests
        /*public String[] getRandomEntries(int num){
            String[] toRet = new String[num];
            for (int i = 0; i < num; i++){                        
                //pick a random bucket, go out a random number 
                Node n = array[(int)Math.random()*M].first;
                int rand = (int)Math.random()*(int)Math.sqrt(num);

                for(int j = 0; j<rand && n.next!= null; j++) n = n.next;
                toRet[i]=n.word;


            }
            return toRet;
        }*/

        class Bucket {

            private Node first;

            public boolean get(String in) {         //return key true if key exists
                Node next = first;
                while (next != null) {
                    if (next.word.equals(in)) {
                        return true;
                    }
                    next = next.next;
                }
                return false;
            }

            public void put(String key) {
                for (Node curr = first; curr != null; curr = curr.next) {
                    if (key.equals(curr.word)) {
                        return;                     //search hit: return
                    }
                }
                first = new Node(key, first); //search miss: add new node
            }

            class Node {

                String word;
                Node next;

                public Node(String key, Node next) {
                    this.word = key;
                    this.next = next;
                }

            }

        }
    }

    public void check() {
        SpellCheck sc = new SpellCheck();
        try
        {
        sc.run();
        }
        catch(Exception e){}
    }

}





























class FileOperation2
{
Notepad2 npd;

boolean saved;
boolean newFileFlag;
String fileName;
String applicationTitle="GROUP_NOTEPAD";

File fileRef;
JFileChooser chooser;
/////////////////////////////
boolean isSave(){return saved;}
void setSave(boolean saved){this.saved=saved;}
String getFileName(){return new String(fileName);}
void setFileName(String fileName){this.fileName=new String(fileName);}
/////////////////////////
FileOperation2(Notepad2 npd)
{
this.npd=npd;

saved=true;
newFileFlag=true;
fileName=new String("Untitled");
fileRef=new File(fileName);
this.npd.f.setTitle(fileName+" - "+applicationTitle);

chooser=new JFileChooser();
//chooser.addChoosableFileFilter(new MyFileFilter(".java","Java Source Files(*.java)"));
//chooser.addChoosableFileFilter(new MyFileFilter(".txt","Text Files(*.txt)"));
chooser.setCurrentDirectory(new File("."));

}
//////////////////////////////////////

boolean saveFile(File temp)
{
FileWriter fout=null;
try
{
fout=new FileWriter(temp);
fout.write(npd.ta.getText());
}
catch(IOException ioe){updateStatus(temp,false);return false;}
finally
{try{fout.close();}catch(IOException excp){}}
updateStatus(temp,true);
return true;
}
////////////////////////
boolean saveThisFile()
{

if(!newFileFlag)
	{return saveFile(fileRef);}

return saveAsFile();
}
////////////////////////////////////
boolean saveAsFile()
{
File temp=null;
chooser.setDialogTitle("Save As...");
chooser.setApproveButtonText("Save Now"); 
chooser.setApproveButtonMnemonic(KeyEvent.VK_S);
chooser.setApproveButtonToolTipText("Click me to save!");

do
{
if(chooser.showSaveDialog(this.npd.f)!=JFileChooser.APPROVE_OPTION)
	return false;
temp=chooser.getSelectedFile();
if(!temp.exists()) break;
if(   JOptionPane.showConfirmDialog(
	this.npd.f,"<html>"+temp.getPath()+" already exists.<br>Do you want to replace it?<html>",
	"Save As",JOptionPane.YES_NO_OPTION
	)==JOptionPane.YES_OPTION)
	break;
}while(true);


return saveFile(temp);
}

////////////////////////
boolean openFile(File temp)
{
FileInputStream fin=null;
BufferedReader din=null;

try
{
fin=new FileInputStream(temp);
din=new BufferedReader(new InputStreamReader(fin));
String str=" ";
while(str!=null)
{
str=din.readLine();
if(str==null)
break;
this.npd.ta.append(str+"\n");
}

}
catch(IOException ioe){updateStatus(temp,false);return false;}
finally
{try{din.close();fin.close();}catch(IOException excp){}}
updateStatus(temp,true);
this.npd.ta.setCaretPosition(0);
return true;
}
///////////////////////
void openFile()
{
if(!confirmSave()) return;
chooser.setDialogTitle("Open File...");
chooser.setApproveButtonText("Open this"); 
chooser.setApproveButtonMnemonic(KeyEvent.VK_O);
chooser.setApproveButtonToolTipText("Click me to open the selected file.!");

File temp=null;
do
{
if(chooser.showOpenDialog(this.npd.f)!=JFileChooser.APPROVE_OPTION)
	return;
temp=chooser.getSelectedFile();

if(temp.exists())	break;

JOptionPane.showMessageDialog(this.npd.f,
	"<html>"+temp.getName()+"<br>file not found.<br>"+
	"Please verify the correct file name was given.<html>",
	"Open",	JOptionPane.INFORMATION_MESSAGE);

} while(true);

this.npd.ta.setText("");

if(!openFile(temp))
	{
	fileName="Untitled"; saved=true; 
	this.npd.f.setTitle(fileName+" - "+applicationTitle);
	}
if(!temp.canWrite())
	newFileFlag=true;

}
////////////////////////
void updateStatus(File temp,boolean saved)
{
if(saved)
{
this.saved=true;
fileName=new String(temp.getName());
if(!temp.canWrite())
	{fileName+="(Read only)"; newFileFlag=true;}
fileRef=temp;
npd.f.setTitle(fileName + " - "+applicationTitle);
npd.statusBar.setText("File : "+temp.getPath()+" saved/opened successfully.");
newFileFlag=false;
}
else
{
npd.statusBar.setText("Failed to save/open : "+temp.getPath());
}
}
///////////////////////
boolean confirmSave()
{
String strMsg="<html>The text in the "+fileName+" file has been changed.<br>"+
	"Do you want to save the changes?<html>";
if(!saved)
{
int x=JOptionPane.showConfirmDialog(this.npd.f,strMsg,applicationTitle,JOptionPane.YES_NO_CANCEL_OPTION);

if(x==JOptionPane.CANCEL_OPTION) return false;
if(x==JOptionPane.YES_OPTION && !saveAsFile()) return false;
}
return true;
}
///////////////////////////////////////
void newFile()
{
if(!confirmSave()) return;

this.npd.ta.setText("");
fileName=new String("Untitled");
fileRef=new File(fileName);
saved=true;
newFileFlag=true;
this.npd.f.setTitle(fileName+" - "+applicationTitle);
}
//////////////////////////////////////
}// end defination of class FileOperation2
/************************************/
public class Notepad2  implements ActionListener, MenuConstants2
{

JFrame f;
JTextArea ta;
JLabel statusBar;

private String fileName="Untitled";
private boolean saved=true;
String applicationName="Javapad";

String searchString, replaceString;
int lastSearchIndex;

FileOperation2 fileHandler;
//FontChooser fontDialog=null;
//FindDialog findReplaceDialog=null; 
JColorChooser bcolorChooser=null;
JColorChooser fcolorChooser=null;
JDialog backgroundDialog=null;
JDialog foregroundDialog=null;
JMenuItem cutItem,copyItem, deleteItem, findItem, findNextItem, replaceItem, gotoItem, selectAllItem;
/****************************/
Notepad2()
{
f=new JFrame(fileName+" - "+applicationName);
ta=new JTextArea(30,60);
statusBar=new JLabel("||       Ln 1, Col 1  ",JLabel.RIGHT);
f.add(new JScrollPane(ta),BorderLayout.CENTER);
f.add(statusBar,BorderLayout.SOUTH);
f.add(new JLabel("  "),BorderLayout.EAST);
f.add(new JLabel("  "),BorderLayout.WEST);
createMenuBar(f);
//f.setSize(350,350);
f.pack();
f.setLocation(100,50);
f.setVisible(true);
f.setLocation(150,50);
f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

fileHandler=new FileOperation2(this);

/////////////////////

ta.addCaretListener(
new CaretListener()
{
public void caretUpdate(CaretEvent e)
{
int lineNumber=0, column=0, pos=0;

try
{
pos=ta.getCaretPosition();
lineNumber=ta.getLineOfOffset(pos);
column=pos-ta.getLineStartOffset(lineNumber);
}catch(Exception excp){}
if(ta.getText().length()==0){lineNumber=0; column=0;}
statusBar.setText("||       Ln "+(lineNumber+1)+", Col "+(column+1));
}
});
//////////////////
DocumentListener myListener = new DocumentListener()
{
public void changedUpdate(DocumentEvent e){fileHandler.saved=false;}
public void removeUpdate(DocumentEvent e){fileHandler.saved=false;}
public void insertUpdate(DocumentEvent e){fileHandler.saved=false;}
};
ta.getDocument().addDocumentListener(myListener);
/////////
WindowListener frameClose=new WindowAdapter()
{
public void windowClosing(WindowEvent we)
{
if(fileHandler.confirmSave())System.exit(0);
}
};
f.addWindowListener(frameClose);
//////////////////
/*
ta.append("Hello dear hello hi");
ta.append("\nwho are u dear mister hello");
ta.append("\nhello bye hel");
ta.append("\nHello");
ta.append("\nMiss u mister hello hell");
fileHandler.saved=true;
*/
}
////////////////////////////////////
void goTo()
{
int lineNumber=0;
try
{
lineNumber=ta.getLineOfOffset(ta.getCaretPosition())+1;
String tempStr=JOptionPane.showInputDialog(f,"Enter Line Number:",""+lineNumber);
if(tempStr==null)
	{return;}
lineNumber=Integer.parseInt(tempStr);
ta.setCaretPosition(ta.getLineStartOffset(lineNumber-1));
}catch(Exception e){}
}
///////////////////////////////////
public void actionPerformed(ActionEvent ev)
{
String cmdText=ev.getActionCommand();
////////////////////////////////////
if(cmdText.equals(fileNew))
	fileHandler.newFile();
else if(cmdText.equals(fileOpen))
	fileHandler.openFile();
////////////////////////////////////
else if(cmdText.equals(fileSave))
	fileHandler.saveThisFile();
////////////////////////////////////
else if(cmdText.equals(fileSaveAs))
	fileHandler.saveAsFile();
////////////////////////////////////
else if(cmdText.equals(fileExit))
	{if(fileHandler.confirmSave())System.exit(0);}
////////////////////////////////////
else if(cmdText.equals(filePrint))
JOptionPane.showMessageDialog(
	Notepad2.this.f,
	"Get ur printer repaired first! It seems u dont have one!",
	"Bad Printer",
	JOptionPane.INFORMATION_MESSAGE
	);
////////////////////////////////////
else if(cmdText.equals(editCut))
	ta.cut();
////////////////////////////////////
else if(cmdText.equals(editCopy))
	ta.copy();
////////////////////////////////////
else if(cmdText.equals(editPaste))
	ta.paste();
////////////////////////////////////
else if(cmdText.equals(editDelete))
	ta.replaceSelection("");
////////////////////////////////////
else if(cmdText.equals(editFind))
{
if(Notepad2.this.ta.getText().length()==0)
	return;	// text box have no text
//if(findReplaceDialog==null)
//	findReplaceDialog=new FindDialog(Notepad2.this.ta);
//findReplaceDialog.showDialog(Notepad2.this.f,true);//find
}
////////////////////////////////////
else if(cmdText.equals(editFindNext))
{
if(Notepad2.this.ta.getText().length()==0)
	return;	// text box have no text

//if(findReplaceDialog==null)
//	statusBar.setText("Nothing to search for, use Find option of Edit Menu first !!!!");
//else
//	findReplaceDialog.findNextWithSelection();
}
////////////////////////////////////
else if(cmdText.equals(editReplace))
{
if(Notepad2.this.ta.getText().length()==0)
	return;	// text box have no text

//if(findReplaceDialog==null)
//	findReplaceDialog=new FindDialog(Notepad2.this.ta);
//findReplaceDialog.showDialog(Notepad2.this.f,false);//replace
}
////////////////////////////////////
else if(cmdText.equals(editGoTo))
{
if(Notepad2.this.ta.getText().length()==0)
	return;	// text box have no text
goTo();
}
////////////////////////////////////
else if(cmdText.equals(editSelectAll))
	ta.selectAll();
////////////////////////////////////
else if(cmdText.equals(editTimeDate))
	ta.insert(new Date().toString(),ta.getSelectionStart());
////////////////////////////////////
else if(cmdText.equals(formatWordWrap))
{
JCheckBoxMenuItem temp=(JCheckBoxMenuItem)ev.getSource();
ta.setLineWrap(temp.isSelected());
}
////////////////////////////////////
else if(cmdText.equals(formatFont))
{
//if(fontDialog==null)
//	fontDialog=new FontChooser(ta.getFont());
//
//if(fontDialog.showDialog(Notepad2.this.f,"Choose a font"))
//	Notepad2.this.ta.setFont(fontDialog.createFont());
}
////////////////////////////////////
else if(cmdText.equals(formatForeground))
	showForegroundColorDialog();
////////////////////////////////////
else if(cmdText.equals(formatBackground))
	showBackgroundColorDialog();
////////////////////////////////////

else if(cmdText.equals(viewStatusBar))
{
JCheckBoxMenuItem temp=(JCheckBoxMenuItem)ev.getSource();
statusBar.setVisible(temp.isSelected());
}
////////////////////////////////////
else if(cmdText.equals(helpAboutNotepad2))
{
JOptionPane.showMessageDialog(Notepad2.this.f,aboutText,"Dedicated 2 u!",JOptionPane.INFORMATION_MESSAGE);
}

else if(cmdText.equals(spellCheckText))
{
//	check();
JOptionPane.showMessageDialog(Notepad2.this.f,aboutText,"Dedicated 2 u!",JOptionPane.INFORMATION_MESSAGE);
SpellCheck sc = new SpellCheck();
//new Notepad2();

sc.check();
}

else
	statusBar.setText("This "+cmdText+" command is yet to be implemented");
}//action Performed
////////////////////////////////////
void showBackgroundColorDialog()
{
if(bcolorChooser==null)
	bcolorChooser=new JColorChooser();
if(backgroundDialog==null)
	backgroundDialog=JColorChooser.createDialog
	(Notepad2.this.f,
	formatBackground,
	false,
	bcolorChooser,
	new ActionListener()
	{public void actionPerformed(ActionEvent evvv){
	Notepad2.this.ta.setBackground(bcolorChooser.getColor());}},
	null);	

backgroundDialog.setVisible(true);
}
////////////////////////////////////
void showForegroundColorDialog()
{
if(fcolorChooser==null)
	fcolorChooser=new JColorChooser();
if(foregroundDialog==null)
	foregroundDialog=JColorChooser.createDialog
	(Notepad2.this.f,
	formatForeground,
	false,
	fcolorChooser,
	new ActionListener()
	{public void actionPerformed(ActionEvent evvv){
	Notepad2.this.ta.setForeground(fcolorChooser.getColor());}},
	null);	

foregroundDialog.setVisible(true);
}

///////////////////////////////////
JMenuItem createMenuItem(String s, int key,JMenu toMenu,ActionListener al)
{
JMenuItem temp=new JMenuItem(s,key);
temp.addActionListener(al);
toMenu.add(temp);

return temp;
}
////////////////////////////////////
JMenuItem createMenuItem(String s, int key,JMenu toMenu,int aclKey,ActionListener al)
{
JMenuItem temp=new JMenuItem(s,key);
temp.addActionListener(al);
temp.setAccelerator(KeyStroke.getKeyStroke(aclKey,ActionEvent.CTRL_MASK));
toMenu.add(temp);

return temp;
}
////////////////////////////////////
JCheckBoxMenuItem createCheckBoxMenuItem(String s, int key,JMenu toMenu,ActionListener al)
{
JCheckBoxMenuItem temp=new JCheckBoxMenuItem(s);
temp.setMnemonic(key);
temp.addActionListener(al);
temp.setSelected(false);
toMenu.add(temp);

return temp;
}
////////////////////////////////////
JMenu createMenu(String s,int key,JMenuBar toMenuBar)
{
JMenu temp=new JMenu(s);
temp.setMnemonic(key);
toMenuBar.add(temp);
return temp;
}
/*********************************/
void createMenuBar(JFrame f)
{
JMenuBar mb=new JMenuBar();
JMenuItem temp;

JMenu fileMenu=createMenu(fileText,KeyEvent.VK_F,mb);
JMenu editMenu=createMenu(editText,KeyEvent.VK_E,mb);
JMenu formatMenu=createMenu(formatText,KeyEvent.VK_O,mb);
JMenu viewMenu=createMenu(viewText,KeyEvent.VK_V,mb);
JMenu helpMenu=createMenu(helpText,KeyEvent.VK_H,mb);
JMenu SpellCheck=createMenu(spellCheckText,KeyEvent.VK_C,mb);

createMenuItem(fileNew,KeyEvent.VK_N,fileMenu,KeyEvent.VK_N,this);
createMenuItem(fileOpen,KeyEvent.VK_O,fileMenu,KeyEvent.VK_O,this);
createMenuItem(fileSave,KeyEvent.VK_S,fileMenu,KeyEvent.VK_S,this);
createMenuItem(fileSaveAs,KeyEvent.VK_A,fileMenu,this);
fileMenu.addSeparator();
temp=createMenuItem(filePageSetup,KeyEvent.VK_U,fileMenu,this);
temp.setEnabled(false);
createMenuItem(filePrint,KeyEvent.VK_P,fileMenu,KeyEvent.VK_P,this);
fileMenu.addSeparator();
createMenuItem(fileExit,KeyEvent.VK_X,fileMenu,this);

temp=createMenuItem(editUndo,KeyEvent.VK_U,editMenu,KeyEvent.VK_Z,this);
temp.setEnabled(false);
editMenu.addSeparator();
cutItem=createMenuItem(editCut,KeyEvent.VK_T,editMenu,KeyEvent.VK_X,this);
copyItem=createMenuItem(editCopy,KeyEvent.VK_C,editMenu,KeyEvent.VK_C,this);
createMenuItem(editPaste,KeyEvent.VK_P,editMenu,KeyEvent.VK_V,this);
deleteItem=createMenuItem(editDelete,KeyEvent.VK_L,editMenu,this);
deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
editMenu.addSeparator();
findItem=createMenuItem(editFind,KeyEvent.VK_F,editMenu,KeyEvent.VK_F,this);
findNextItem=createMenuItem(editFindNext,KeyEvent.VK_N,editMenu,this);
findNextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
replaceItem=createMenuItem(editReplace,KeyEvent.VK_R,editMenu,KeyEvent.VK_H,this);
gotoItem=createMenuItem(editGoTo,KeyEvent.VK_G,editMenu,KeyEvent.VK_G,this);
editMenu.addSeparator();
selectAllItem=createMenuItem(editSelectAll,KeyEvent.VK_A,editMenu,KeyEvent.VK_A,this);
createMenuItem(editTimeDate,KeyEvent.VK_D,editMenu,this).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));

createCheckBoxMenuItem(formatWordWrap,KeyEvent.VK_W,formatMenu,this);

createMenuItem(formatFont,KeyEvent.VK_F,formatMenu,this);
formatMenu.addSeparator();
createMenuItem(formatForeground,KeyEvent.VK_T,formatMenu,this);
createMenuItem(formatBackground,KeyEvent.VK_P,formatMenu,this);

createCheckBoxMenuItem(viewStatusBar,KeyEvent.VK_S,viewMenu,this).setSelected(true);
/************For Look and Feel, May not work properly on different operating environment***/
//LookAndFeelMenu.createLookAndFeelMenuItem(viewMenu,this.f);


temp=createMenuItem(helpHelpTopic,KeyEvent.VK_H,helpMenu,this);
temp.setEnabled(false);
helpMenu.addSeparator();
createMenuItem(helpAboutNotepad2,KeyEvent.VK_A,helpMenu,this);
createMenuItem(spellCheckText,KeyEvent.VK_A,SpellCheck,this);
MenuListener editMenuListener=new MenuListener()
{
   public void menuSelected(MenuEvent evvvv)
	{
	if(Notepad2.this.ta.getText().length()==0)
	{
	findItem.setEnabled(false);
	findNextItem.setEnabled(false);
	replaceItem.setEnabled(false);
	selectAllItem.setEnabled(false);
	gotoItem.setEnabled(false);
	}
	else
	{
	findItem.setEnabled(true);
	findNextItem.setEnabled(true);
	replaceItem.setEnabled(true);
	selectAllItem.setEnabled(true);
	gotoItem.setEnabled(true);
	}
	if(Notepad2.this.ta.getSelectionStart()==ta.getSelectionEnd())
	{
	cutItem.setEnabled(false);
	copyItem.setEnabled(false);
	deleteItem.setEnabled(false);
	}
	else
	{
	cutItem.setEnabled(true);
	copyItem.setEnabled(true);
	deleteItem.setEnabled(true);
	}
	}
   public void menuDeselected(MenuEvent evvvv){}
   public void menuCanceled(MenuEvent evvvv){}
};
editMenu.addMenuListener(editMenuListener);
f.setJMenuBar(mb);
}
/*************Constructor**************/
////////////////////////////////////
public static void main(String[] s)
{
//	SpellCheck sc = new SpellCheck();
	new Notepad2();
	

//	sc.check();


}





}
/**************************************/
//public
interface MenuConstants2
{
final String fileText="File";
final String editText="Edit";
final String formatText="Format";
final String viewText="View";
final String helpText="Help";

final String spellCheckText="SpellCheck";

final String fileNew="New";
final String fileOpen="Open...";
final String fileSave="Save";
final String fileSaveAs="Save As...";
final String filePageSetup="Page Setup...";
final String filePrint="Print";
final String fileExit="Exit";

final String editUndo="Undo";
final String editCut="Cut";
final String editCopy="Copy";
final String editPaste="Paste";
final String editDelete="Delete";
final String editFind="Find...";
final String editFindNext="Find Next";
final String editReplace="Replace";
final String editGoTo="Go To...";
final String editSelectAll="Select All";
final String editTimeDate="Time/Date";

final String formatWordWrap="Word Wrap";
final String formatFont="Font...";
final String formatForeground="Set Text color...";
final String formatBackground="Set Pad color...";

final String viewStatusBar="Status Bar";

final String helpHelpTopic="Help Topic";
final String helpAboutNotepad2="About Javapad";

final String aboutText=
	"<html><big>Your Javapad</big><hr><hr>"
	+"<p align=right>OUTPUT IN CMD!"
	

	+"<strong>Thanx for using Javapad</strong><br>"
	

	+"<html>";
}