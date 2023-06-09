/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package thread;

/**
 *
 * @author agils
 */
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class ExchangeRate extends javax.swing.JFrame {

 String API_PROVDIER = 
"https://api.apilayer.com/exchangerates_data";
String[] code = {"MXN","AUD","HKD","RON","HRK","CHF","IDR",
"CAD","USD","JPY","BRL","PHP","CZK","NOK","INR","PLN","ISK","MYR","ZAR","ILS",
"GBP","SGD","HUF","EUR","CNY","TRY","SEK","RUB",
"NZD","KRW","THB","BGN","DKK"};
 DefaultListModel<String> model = new DefaultListModel<>();
 List<String> rates = new ArrayList<>();




    
    private void initBase(){
    listRates.setModel(model);
    cmbBase.removeAllItems();
    cmbBase.addItem("--Select Base");
    for(String str: code){
        cmbBase.addItem(str);
    }
}
    private void startClock(){
        Timer t=new Timer("Thred-jam");
        t.schedule(new TimerTask(){
            public void run(){
                Calendar c=Calendar.getInstance();
                SimpleDateFormat df=new
                SimpleDateFormat("HH:mm:SS");
                String waktu=df.format(c.getTime());
            }
        },0,1000);
    }
    private void loading(boolean b){
        loading.setVisible(b);
    }
    private void addResponseToList(String base){
        CurrencyConversionResponse response=
                getResponse(API_PROVDIER+""
                +"/latest?"
                +"apikey=bnEmv1gtYo4zxIWOP6EEaKwzuvjXbLZf"
                +"&base="+base+"");
        if(response !=null){
    rates.clear();
    for(String str:code){
        String rate=response.getRates().get(str);
        String item=str+"\t:+ rate";
        rates.add(item);
    }
    }
}
    private static CurrencyConversionResponse
            getResponse(String strUrl){
                CurrencyConversionResponse response=null;
                Gson gson=new Gson();
                StringBuilder sb=new StringBuilder();
                if(strUrl==null || strUrl.isEmpty()){
                    System.out.println("Application Error");
                    return null;
                }
                URL url;
                try{
                    url=new URL(strUrl);
                    HttpURLConnection connection=
                   (HttpURLConnection) url.openConnection();
                    try(InputStream stream=
                            connection.getInputStream()){
                        int data=stream.read();
                        while(data!=-1){
                            sb.append((char)data);
                            data=stream.read();
                        }
                    }
                    response=gson.fromJson(sb.toString(),
                            CurrencyConversionResponse.class);
                }catch(MalformedURLException e){
                System.out.println(e.getMessage());
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
                return response;
            }
            class Exchange extends SwingWorker<Object, Object>{
                public Exchange(String name){
                    setName(name);
                    System.out.println(name+"=>Dijalankan");
                }
                protected Object doInBackground() throws Exception{
                    String base=
                            cmbBase.getSelectedItem().toString();
                    addResponseToList(base);
                    return 0;
                }
                protected void done(){
                    model.clear();
                    rates.forEach((rate)->{
                        model.addElement(rate);
                    });
                    loading(false);
                    System.out.println(getName()+"=>DIhentikan");
                }
            }
    
    public ExchangeRate() {
        initComponents();
        initBase();
        startClock();
        loading(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClock = new javax.swing.JLabel();
        pembatas = new javax.swing.JSeparator();
        cmbBase = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listRates = new javax.swing.JList<>();
        loading = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblClock.setForeground(new java.awt.Color(0, 153, 51));
        lblClock.setText("     <CLOCK>");
        getContentPane().add(lblClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 15, 124, -1));
        getContentPane().add(pembatas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 58, 412, 10));

        cmbBase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" }));
        cmbBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBaseActionPerformed(evt);
            }
        });
        getContentPane().add(cmbBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 80, 274, 43));

        jLabel1.setText("BASE :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 89, 57, -1));

        listRates.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listRates);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 129, 364, 216));
        getContentPane().add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 363, 412, 17));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBaseActionPerformed
        int index=cmbBase.getSelectedIndex();
        if(index>0){
            loading(true);
            new Exchange("Thread-Exchange").execute();
        }else{
            model.clear();
        }
    }//GEN-LAST:event_cmbBaseActionPerformed

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
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExchangeRate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClock;
    private javax.swing.JList<String> listRates;
    private javax.swing.JProgressBar loading;
    private javax.swing.JSeparator pembatas;
    // End of variables declaration//GEN-END:variables
}
