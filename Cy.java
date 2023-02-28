Code (Text): public String currencyName = "AKCredits"; 
//I named it "AKCredits" because it is named after my character. Of course you can name it anything you want.     

public Methods methods; Spoiler: Config Code (Text): //Create Config   

  saveDefaultConfig();         
    getConfig().set("currency-name", currencyName);         
    saveConfig(); Spoiler: BalanceManager 


Code (Text): public double getBalance(Player p) {         
m.getConfig().getDouble(p.getName() + "-money");  


return m.getConfig().getDouble(p.getName() + "-money"); }

public double addToBalance(Player p,double numberToAddTo) {         
double sum = getBalance(p) + numberToAddTo;         
m.getConfig().set(p.getName() + "-money", sum);  
 
 
return sum;     }     public double setBalance(Player p, double numberToSet) {        
m.getConfig().set(p.getName() + "-money", numberToSet);  
  
return numberToSet;     }     
public double subtractFromBalance(Player p, double numberToSubtractFrom) {         
double subtract = getBalance(p) - numberToSubtractFrom;         
m.getConfig().set(p.getName() + "-money", subtract);    
  
return subtract;     } 
Spoiler: Event Code Code (Text): @EventHandler    

public void onJoin(PlayerJoinEvent e) {         
Player p = e.getPlayer();         
if (!p.hasPlayedBefore()) {             
main.getConfig().set(p.getName() + "-money", 0.0);             
  main.saveConfig();         }     } 
@EventHandler public void onQuit(PlayerQuitEvent e) { main.saveConfig(); } 
Spoiler: Commands Code (Text): @Override     
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {         
  Player p = (Player) sender;         
  if (cmd.getName().equalsIgnoreCase("balance")) {             
    if (sender instanceof Player) {                 
      if (args.length == 0) {                     
p.sendMessage(currencyName + ": " + String.valueOf(methods.getBalance(p)));                 } 
      else if (args.length > 0) {                     
        if (args[0].equalsIgnoreCase("help")) {                         
          p.sendMessage("/balance - To display account balance.");                         
          p.sendMessage("/balance help - To display this help page.");                         
          p.sendMessage("/balance add <PlayerName> <NumberToAddTo> - Adds to the players balance.");                         
          p.sendMessage("/balance withdraw <PlayerName> <NumberToTakeFrom> - Takes from the players balance.");                         
          p.sendMessage("/balance set <PlayerName> <NumberToSetTo> - Set the players account in total.");                     } 
        else if (args[0].equalsIgnoreCase("add")) {                         
          try {                             
            if (Bukkit.getPlayer(args[1]) != null) {                                 
              methods.addToBalance(Bukkit.getPlayer(args[1]), Double.parseDouble(args[2]));                                 
              p.sendMessage("Successfully add " + args[2] + "to " + args[1] + "'s account");                             } 
            else {                                 
              p.sendMessage("That is not a valid player or that player is not online");                             }                         } 
          catch (NumberFormatException e) {                             
            p.sendMessage("Make sure it is a double and a player name");                             
            p.sendMessage("Correct syntax: /balance add <PlayerName> <NumberToAddTo>");                            
            p.sendMessage("For Example: 3.9");                         }                     } 
        else if (args[0].equalsIgnoreCase("take") || args[0].equalsIgnoreCase("withdraw")) {                         
          try {                             if (Bukkit.getPlayer(args[1]) != null) {                                 
            methods.subtractFromBalance(Bukkit.getPlayer(args[1]), Double.parseDouble(args[2]));                                 
            p.sendMessage("Successfully subtracted " + args[2] + "from " + args[1] + "'s account");                             }  
        else {                                 
          p.sendMessage("That is not a valid player or that player is not online");                             }                         } 
          catch (NumberFormatException e) {                             
            p.sendMessage("Make sure it is a double and a player name");                             
            p.sendMessage("Correct syntax: /balance take <PlayerName> <NumberToTakeFrom>");                            
            p.sendMessage("For Example: 3.9");                         }                     } 
        else if (args[0].equalsIgnoreCase("set")) {                         
          try {                             
            if (Bukkit.getPlayer(args[1]) != null) {                                 
              methods.setBalance(Bukkit.getPlayer(args[1]), Double.parseDouble(args[2]));                                 
              p.sendMessage("Set " + args[1]+"'s"+" balance to " + args[2]);                             }                             
            else {                                 
              p.sendMessage("That is not a valid player or that player is not online");                             }                         } 
          catch (NumberFormatException e) {                         }                         
          p.sendMessage("Make sure it is a double and a player name");                         
          p.sendMessage("Correct syntax: /balance set <PlayerName> <NumberToSet>");                         
          p.sendMessage("For Example: 3.9");                     }                 }             }         }         
  return false;     } 
Code (Text): getCommand("balance").setExecutor(this); 
getCommand("bal").setExecutor(this); 

(Text): saveConfig(); Here is the plugin.yml You can change the name of the plugin. Spoiler: plugin.yml Code 

(Text): name: AKCredits version: 1.0 main: me.AKZOMBIE74.AKCredits commands:   balance:     description: Do /balance help     aliases: [balx]
