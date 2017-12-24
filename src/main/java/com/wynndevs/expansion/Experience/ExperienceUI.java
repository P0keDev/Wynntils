package com.wynndevs.expansion.Experience;

import com.wynndevs.ModCore;
import com.wynndevs.expansion.ExpReference;
import com.wynndevs.expansion.Misc.Delay;
import com.wynndevs.expansion.Misc.ModGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.text.DecimalFormat;



public class ExperienceUI extends ModGui {
	
	public static boolean ExpAboveHealth = false;
	public static boolean ExpFlowPercentage = false;
	public static boolean ExpFlowSlow = false;
	public static boolean UseExpInstead = false;
	public static boolean ExpFlowSmall = false;
	public static boolean EnableSidebar = false;
	public static boolean EnableScrollingSidebar = false;
	public static boolean StaticBarShadow = false;
	public static boolean SideBarHeaderShadow = false;
	public static boolean SideBarFeedShadow = false;
	public static boolean KillStreak = false;
	public static boolean KillPerMinute = false;
	public static boolean ExpFlowShowNames = false;
	public static boolean ExpFlowShowLevel = false;
	
	static int ExpHUD = 0;
	static float ExpHUDPer = 0;
	static int ExpHUDCombo = 0;
	static int ExpHUDAnimation = 0;
	private static String ExpHUDMessage = "";
	static int ExpHUDLength = 0;
	private static long KillTimeStamp = 0L;
	private static String KillsPerMinute = "-";
	private static Delay ExpHUDDelay = new Delay(0.025f,true);
	static Delay ExpHUDHang = new Delay(30.0f,false);
	private static Delay ExpFlowDelay = new Delay(0.06f,false);
	private static Delay ExpFlowDelaySlow = new Delay(0.12f,false);
	
	public ExperienceUI(Minecraft mc) {
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		FontRenderer font = mc.fontRenderer;
		
		// Bug fix for Exp showing sometimes when switching class
		if (mc.player.experienceLevel < 1){
			if (ExpHUDAnimation != 0 || !Experience.Exp.isEmpty()){
				ExpHUD = 0;
				ExpHUDPer = 0;
				ExpHUDCombo = 0;
				ExpHUDAnimation = 0;
				ExpHUDLength = 0;
				KillTimeStamp = 0;
				KillsPerMinute = "-";
				Experience.Exp.clear();
			}
		}
		
		
		// Constant Exp Amount
		if (Experience.getCurrentWynncraftMaxXp() != -1) {
			if (ExpAboveHealth){
				if (StaticBarShadow){
					this.drawCenteredString(font,String.valueOf('\u00a7') + "a   " + (UseExpInstead ? "Exp " : " XP ") + String.valueOf('\u00a7') + "2[" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftXp())) + String.valueOf('\u00a7') + "2/" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftMaxXp())) + String.valueOf('\u00a7') + "2] " + (new DecimalFormat("##.##").format(ModCore.mc().player.experience * 100)) + "%",(width/2),(height - 85),1.0f,Integer.parseInt("55ff55",16));
				}else{
					this.drawCenteredStringPlain(font,String.valueOf('\u00a7') + "a   " + (UseExpInstead ? "Exp " : " XP ") + String.valueOf('\u00a7') + "2[" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftXp())) + String.valueOf('\u00a7') + "2/" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftMaxXp())) + String.valueOf('\u00a7') + "2] " + (new DecimalFormat("##.##").format(ModCore.mc().player.experience * 100)) + "%",(width/2),(height - 85),1.0f,Integer.parseInt("55ff55",16));
				}
			}else{
				if (StaticBarShadow){
					this.drawCenteredString(font,String.valueOf('\u00a7') + "a   " + (UseExpInstead ? "Exp " : " XP ") + String.valueOf('\u00a7') + "2[" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftXp())) + String.valueOf('\u00a7') + "2/" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftMaxXp())) + String.valueOf('\u00a7') + "2] " + (new DecimalFormat("##.##").format(ModCore.mc().player.experience * 100)) + "%",(width/2),(height - 48),1.0f,Integer.parseInt("55ff55",16));
				}else{
					this.drawCenteredStringPlain(font,String.valueOf('\u00a7') + "a   " + (UseExpInstead ? "Exp " : " XP ") + String.valueOf('\u00a7') + "2[" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftXp())) + String.valueOf('\u00a7') + "2/" + String.valueOf('\u00a7') + 'a' + new DecimalFormat("#,###,###,##0").format(Math.round(Experience.getCurrentWynncraftMaxXp())) + String.valueOf('\u00a7') + "2] " + (new DecimalFormat("##.##").format(ModCore.mc().player.experience * 100)) + "%",(width/2),(height - 48),1.0f,Integer.parseInt("55ff55",16));
				}
			}
		}
		
		// Rest ExpHUDHang
		// Add XP to ExpHUD
		// Add %XP to ExpHUDPer
		// Set ExpHUDAnimation = 0
		
		// Accumulative Exp Amount
		if(EnableSidebar){
			if (ExpHUDAnimation < ExpHUDLength) {
				if (ExpHUDDelay.Passed() && ExpHUDHang.Passed()) {
					ExpHUDAnimation++;
				}
				
				ExpHUDMessage = String.valueOf('\u00a7') + "a+" + (UseExpInstead ? "Exp " : "XP ") + String.valueOf('\u00a7') + "2 [" + String.valueOf('\u00a7') + "a+" + new DecimalFormat("#,###,###,##0").format(ExpHUD) + String.valueOf('\u00a7') + "2] " + new DecimalFormat("##,###,#00.00").format(ExpHUDPer) + "%";
				ExpHUDLength = ExpReference.GetMsgLength(ExpHUDMessage, (ExpFlowSmall ? 1.0f : 1.5f)) + 2;
				
				if (KillPerMinute) {
					KillsPerMinute = new DecimalFormat("00.0").format(((float) ExpHUDCombo) / (System.currentTimeMillis() - KillTimeStamp < 60000L ? 1f : (((float) (System.currentTimeMillis() - KillTimeStamp)) / 60000f)));
				}
				
				if (SideBarHeaderShadow){
					this.drawString(font, ExpHUDMessage, (width - ExpHUDLength + ExpHUDAnimation), (KillStreak ? (height/2) - 15 : (height/2)), (ExpFlowSmall ? 1.0f : 1.5f), Integer.parseInt("FFA700",16));
					if (KillStreak) {
						String KillStreakMessage = String.valueOf('\u00a7') + "c" + ExpHUDCombo + " Kill" + (ExpHUDCombo > 1 ? "s" : "") + (KillPerMinute ? String.valueOf('\u00a7') + "4 [" + String.valueOf('\u00a7') + "c" + KillsPerMinute + " Kpm" + String.valueOf('\u00a7') + "4]": "");
						this.drawString(font, KillStreakMessage, (width - ExpReference.GetMsgLength(KillStreakMessage, (ExpFlowSmall ? 1.0f : 1.5f)) - 2 + ExpHUDAnimation), (height/2), (ExpFlowSmall ? 1.0f : 1.5f), Integer.parseInt("FFA700",16));
					}
				}else{
					this.drawStringPlain(font, ExpHUDMessage, (width - ExpHUDLength + ExpHUDAnimation), (KillStreak ? (height/2) - 15 : (height/2)), (ExpFlowSmall ? 1.0f : 1.5f), Integer.parseInt("FFA700",16));
					if (KillStreak) {
						String KillStreakMessage = String.valueOf('\u00a7') + "c" + ExpHUDCombo + " Kill" + (ExpHUDCombo > 1 ? "s" : "") + (KillPerMinute ? String.valueOf('\u00a7') + "4 [" + String.valueOf('\u00a7') + "c" + KillsPerMinute + " Kpm" + String.valueOf('\u00a7') + "4]": "");
						this.drawStringPlain(font, KillStreakMessage, (width - ExpReference.GetMsgLength(KillStreakMessage, (ExpFlowSmall ? 1.0f : 1.5f)) - 2 + ExpHUDAnimation), (height/2), (ExpFlowSmall ? 1.0f : 1.5f), Integer.parseInt("FFA700",16));
					}
				}
			}else if (ExpHUDCombo > 0){
				ExpHUD = 0;
				ExpHUDPer = 0;
				ExpHUDCombo = 0;
				ExpHUDAnimation = 0;
				ExpHUDLength = 0;
				KillTimeStamp = 0;
				KillsPerMinute = "-";
			}
		}
		
		// Scrolling Exp Amounts
		if (!Experience.Exp.isEmpty()){
			boolean ShowAnother = true;
			for(int i=0;i<Experience.Exp.size();i++){
				String[] Exp = Experience.Exp.get(i);
				if (Exp.length < 6){
					Exp = new String[] {Exp[0], Exp[1], Exp[2], Exp[3], "-1", "-"};
					Experience.Exp.set(i, Exp);
					
					try {
						if (Exp[1].contains("[Lv. ")) {
							ExpHUDCombo++;
							if (KillTimeStamp == 0L) KillTimeStamp = System.currentTimeMillis();
						}
					}catch(Exception ignore){}
				}
				if (EnableScrollingSidebar) {
					if (Integer.parseInt(Exp[4]) < 0){
						if (ShowAnother){
							Exp[4] = "0";
							Experience.Exp.set(i, Exp);
							ShowAnother = false;
							ExpHUDHang.Reset();
							ExpHUDAnimation = 0;
							ExpHUDLength = 1;
						}
					}else{
						
						String ExpMsg = (ExpFlowShowNames ? Exp[0] + " " : "") + (ExpFlowShowLevel ? Exp[1] + " " : "") + String.valueOf('\u00a7') + "2[" + String.valueOf('\u00a7') + "a+" + Exp[2] + String.valueOf('\u00a7') + "2]" + (ExpFlowPercentage ? " " + Exp[3] + "%" : "");
						
						if (Exp[5].equals("-")){
							Exp[5] = String.valueOf(ExpReference.GetMsgLength(ExpMsg, (ExpFlowSmall ? 0.75f : 1.0f)) + 2);
							Experience.Exp.set(i, Exp);
						}
						if (ExpFlowSlow){
							if (ExpFlowDelaySlow.Passed()){
								Exp[4] = String.valueOf(Integer.parseInt(Exp[4]) +1);
								Experience.Exp.set(i, Exp);
							}
						}else{
							if (ExpFlowDelay.Passed()){
								Exp[4] = String.valueOf(Integer.parseInt(Exp[4]) +1);
								Experience.Exp.set(i, Exp);
							}
						}
						
						if (ShowAnother && Integer.parseInt(Exp[4]) < 9){ShowAnother = false;}
						
						//System.out.println("Exp :" + Exp[0] + " - " + Exp[1] + " - " + Exp[2] + " - " + Exp[3] + " - " + Exp[4]);
						if (SideBarFeedShadow){
							this.drawString(font, ExpMsg, (width - Integer.parseInt(Exp[5])), (height/2) +15 + Integer.parseInt(Exp[4]), (ExpFlowSmall ? 0.75f : 1.0f), Integer.parseInt("FFA700",16));
						}else{
							this.drawStringPlain(font, ExpMsg, (width - Integer.parseInt(Exp[5])), (height/2) +15 + Integer.parseInt(Exp[4]), (ExpFlowSmall ? 0.75f : 1.0f), Integer.parseInt("FFA700",16));
						}
						
						if (Integer.parseInt(Exp[4]) > height/2){
							Experience.Exp.remove(i);
							i--;
						}
						
					}
				}else{
					Experience.Exp.remove(i);
					i--;
				}
			}
			if (ExpFlowSlow){
				if (ExpFlowDelaySlow.Passed()){ExpFlowDelaySlow.Reset();}
			}else{
				if (ExpFlowDelay.Passed()){ExpFlowDelay.Reset();}
			}
		}
	}
}