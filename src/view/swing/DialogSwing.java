package view.swing;


import control.MCController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Currency;
import model.Money;
import view.Dialog;


public class DialogSwing extends JPanel implements Dialog {
    private final String CALCULATE_BUTTON_LABEL = "Calculate";
    private final String MONEY_LABEL = " Money";
    private final String CURRENCY_FROM_LABEL = " Currency from";
    private final String CURRENCY_TO_LABEL = " Currency to";
    private final String RESULT_LABEL = " Exchange rate";
    private final int MONEY_WIDTH = 40;

    private JLabel moneyLabel, currencyFromLabel, currencyToLabel, resultLabel,
            resultRefreshLabel;
    private JTextField moneyTextField;
    private JComboBox<Currency> currencyFromCombo;
    private JComboBox<Currency> currencyToCombo;
    private JButton calculateButton;

    private List<Currency> currencies;
    
    public DialogSwing(List<Currency> currencies) {
        this.currencies = currencies;
        createComponentsGUI();
    }

    private Currency getCurrencyFrom() {
        return (Currency) this.currencyFromCombo.getSelectedItem();
    }  
    
    @Override
    public Currency getCurrencyTo() {
        return (Currency) this.currencyToCombo.getSelectedItem();
    }
    
    @Override
    public Money getMoney() {
        return new Money(Double.parseDouble(this.moneyTextField.getText()), 
                this.getCurrencyFrom());
    }

    public void registerController(MCController mcController) {
        this.calculateButton.addActionListener((ActionListener) mcController);        
    }
    
    private void createComponentsGUI() {
        this.setPreferredSize(new Dimension(550,200));
        
        this.moneyLabel = new JLabel(this.MONEY_LABEL);
        this.moneyTextField = new JTextField(this.MONEY_WIDTH);
        
        this.currencyFromLabel = new JLabel(this.CURRENCY_FROM_LABEL);
        this.currencyFromCombo = new JComboBox<Currency>();
        this.currencyToLabel = new JLabel(this.CURRENCY_TO_LABEL);
        this.currencyToCombo = new JComboBox<Currency>();
        
        this.resultLabel = new JLabel(this.RESULT_LABEL);
        this.resultRefreshLabel = new JLabel("");
        this.calculateButton = new JButton(this.CALCULATE_BUTTON_LABEL);
                
        for (Currency currency : this.currencies) {
            this.currencyFromCombo.addItem(currency);
            this.currencyToCombo.addItem(currency);
        }
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.X_AXIS));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 1));
        labelPanel.add(this.moneyLabel);
        labelPanel.add(this.currencyFromLabel);
        labelPanel.add(this.currencyToLabel);
        labelPanel.add(this.resultLabel);

        dialogPanel.add(labelPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(0, 1));
        fieldPanel.add(this.moneyTextField);
        fieldPanel.add(this.currencyFromCombo);
        fieldPanel.add(this.currencyToCombo);
        fieldPanel.add(this.resultRefreshLabel);

        dialogPanel.add(fieldPanel);

        JPanel controlPanel = new JPanel();
        controlPanel.add(this.calculateButton);

        setLayout(new BorderLayout());
        add(dialogPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);           
    }
    
    
    @Override
    public void refresh(Money money) {
        this.resultRefreshLabel.setText(money.getAmount() + " " +
                money.getCurrency().getSymbol());
    }
}
