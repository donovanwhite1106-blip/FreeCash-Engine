package com.example.remotebitcoin;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private final int bg = Color.rgb(10, 16, 25), panel = Color.rgb(22, 32, 46), muted = Color.rgb(160, 174, 192), green = Color.rgb(72, 210, 143), white = Color.WHITE;
    private LinearLayout root, content;
    private TextView navHome, navEarn, navWithdraw;
    private EditText invoiceInput;

    @Override public void onCreate(Bundle state) { super.onCreate(state); getWindow().setStatusBarColor(bg); getWindow().setNavigationBarColor(bg); showHome(); }
    private GradientDrawable shape(int color, int radius) { GradientDrawable d = new GradientDrawable(); d.setColor(color); d.setCornerRadius(radius); return d; }
    private TextView text(String s, int size, int color, boolean bold) { TextView t = new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color); if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD); return t; }
    private LinearLayout column() { LinearLayout l = new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); return l; }
    private LinearLayout row() { LinearLayout l = new LinearLayout(this); l.setOrientation(LinearLayout.HORIZONTAL); l.setGravity(Gravity.CENTER_VERTICAL); return l; }
    private void base(String title, String subtitle) {
        root = column(); root.setBackgroundColor(bg); root.setPadding(22, 22, 22, 12);
        TextView brand = text("FREECASH ENGINE", 12, green, true); root.addView(brand);
        TextView h = text(title, 27, white, true); LinearLayout.LayoutParams hp = new LinearLayout.LayoutParams(-1,-2); hp.topMargin=10; root.addView(h,hp);
        TextView sub = text(subtitle, 13, muted, false); LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1,-2); sp.topMargin=4; root.addView(sub,sp);
        content = column(); LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1,0,1); cp.topMargin=20; root.addView(content,cp);
        LinearLayout nav = row(); nav.setPadding(0,12,0,4); nav.setGravity(Gravity.CENTER);
        navHome = navItem("Dashboard", () -> showHome()); navEarn = navItem("Discover", () -> showEarn()); navWithdraw = navItem("Withdraw", () -> showWithdraw());
        nav.addView(navHome,new LinearLayout.LayoutParams(0,-2,1)); nav.addView(navEarn,new LinearLayout.LayoutParams(0,-2,1)); nav.addView(navWithdraw,new LinearLayout.LayoutParams(0,-2,1)); root.addView(nav);
        setContentView(root);
    }
    private TextView navItem(String label, Runnable action) { TextView t=text(label,13,white,true); t.setGravity(Gravity.CENTER); t.setPadding(4,12,4,12); t.setOnClickListener(v->action.run()); return t; }
    private void card(String title, String body) { LinearLayout c=column(); c.setPadding(16,15,16,15); c.setBackground(shape(panel,22)); TextView a=text(title,16,white,true); c.addView(a); TextView b=text(body,13,muted,false); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.topMargin=7; c.addView(b,p); LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(-1,-2); cp.bottomMargin=12; content.addView(c,cp); }
    private void button(String label, Runnable action) { TextView b=text(label,14,bg,true); b.setGravity(Gravity.CENTER); b.setPadding(14,14,14,14); b.setBackground(shape(green,18)); b.setOnClickListener(v->action.run()); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.topMargin=12; content.addView(b,p); }
    private void showHome() {
        base("Your earning hub", "One place for legitimate rewards and payouts.");
        LinearLayout balance=column(); balance.setPadding(18,20,18,20); balance.setBackground(shape(panel,24));
        balance.addView(text("CONFIRMED PAYABLE BALANCE",11,muted,true)); TextView amount=text("$0.00",36,white,true); LinearLayout.LayoutParams ap=new LinearLayout.LayoutParams(-1,-2); ap.topMargin=8; balance.addView(amount,ap);
        TextView note=text("No provider accounts connected yet. No earnings are being claimed.",12,muted,false); LinearLayout.LayoutParams np=new LinearLayout.LayoutParams(-1,-2); np.topMargin=6; balance.addView(note,np); content.addView(balance);
        card("Discovery status", "Provider discovery is not connected yet. The app will only count rewards confirmed by an integrated provider.");
        card("Earning categories", "Research · surveys · tasks · cashback · referrals · authorized computing · mining · promotions");
        card("Withdrawals", "Lightning is planned. Other payout rails can be added when a provider supports them. No app-imposed minimum is intended.");
        button("Explore earning categories", () -> showEarn());
    }
    private void showEarn() {
        base("Discover opportunities", "A broad earning directory — not a promise of guaranteed income.");
        card("Surveys & research", "Connect approved research and survey partners. Eligibility, location, age, and availability vary.");
        card("Tasks & offers", "Rewarded tasks and offers must be completed under each provider's terms. No automated fake engagement.");
        card("Cashback & promotions", "Track eligible purchases and promotional rewards. Never spend money solely to chase an uncertain reward.");
        card("Referrals", "Use only genuine referrals where the program permits them. No spam or fabricated accounts.");
        card("Authorized computing & mining", "Only use computing resources you own or are explicitly authorized to use. Pool endpoints alone do not provide free hashpower.");
        card("Provider integration status", "No earning provider is currently connected. Provider API credentials and payout agreements are required before real balances can be credited.");
        button("How provider connections work", () -> new android.app.AlertDialog.Builder(this).setTitle("Provider connections").setMessage("Each provider needs a documented API or partner integration, user authorization, reward verification, and a real payout method. This build does not scrape accounts, bypass provider rules, or invent rewards.").setPositiveButton("OK",null).show());
    }
    private void showWithdraw() {
        base("Withdraw funds", "Lightning first · zero app-imposed minimum.");
        card("Available to withdraw", "$0.00 confirmed. Pending or estimated rewards are never withdrawable.");
        content.addView(text("Lightning invoice",13,white,true)); invoiceInput=new EditText(this); invoiceInput.setSingleLine(false); invoiceInput.setMinLines(2); invoiceInput.setTextColor(white); invoiceInput.setHintTextColor(muted); invoiceInput.setHint("Paste a Lightning invoice (lnbc…)"); invoiceInput.setPadding(14,12,14,12); invoiceInput.setBackground(shape(panel,16)); LinearLayout.LayoutParams ip=new LinearLayout.LayoutParams(-1,-2); ip.topMargin=8; content.addView(invoiceInput,ip);
        button("Validate invoice", () -> { String s=invoiceInput.getText().toString().trim(); if(!s.toLowerCase().startsWith("lnbc") && !s.toLowerCase().startsWith("lntb")) { toast("Enter a valid-looking mainnet or testnet Lightning invoice."); } else { new android.app.AlertDialog.Builder(this).setTitle("Payout not enabled").setMessage("The invoice format looks plausible, but no funded provider or Lightning wallet backend is connected. No payment has been sent.").setPositiveButton("OK",null).show(); } });
        card("Important", "Invoice format checking is not payment verification. Actual payments require a connected Lightning wallet/provider, sufficient confirmed funds, routing liquidity, and fee disclosure. The app cannot promise a zero-fee or arbitrarily tiny payment.");
    }
    private void toast(String s) { Toast.makeText(this,s,Toast.LENGTH_LONG).show(); }
}
