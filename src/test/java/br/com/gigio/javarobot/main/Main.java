package br.com.gigio.javarobot.main;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;
import org.apache.commons.lang.StringUtils;
import org.jnativehook.GlobalScreen;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Main m = new Main();
        final List<WindowInfo> inflList = new ArrayList<WindowInfo>();
        final List<Integer> order = new ArrayList<Integer>();
        int top = User32.instance.GetTopWindow(0);
//        User32.instance.GetWindowRect();
        while (top != 0) {
            order.add(top);
            top = User32.instance.GetWindow(top, User32.GW_HWNDNEXT);
        }
        final User32 instanceTemp = (User32) Native.loadLibrary ("user32", User32.class);
//        instanceTemp.GetWindow();
//        User32.instance.
        String osName = System.getProperty("os.name");
//        KeyboardFocusManager
        Window win = javax.swing.FocusManager.getCurrentManager().getFocusedWindow();
        Window win2 = javax.swing.FocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
        Window win3 = javax.swing.FocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
        Window win4 = javax.swing.FocusManager.getCurrentManager().getActiveWindow();
        Container container = javax.swing.FocusManager.getCurrentKeyboardFocusManager().getCurrentFocusCycleRoot();
        Window win5 = java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
        Window win6 = java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
//        GlobalScreen.addNativeKeyListener();
//        javax.swing.FocusManager.getCurrentManager().getFocusedWindow();
        Window[] ws = Window.getWindows();
        User32.instance.EnumWindows(new WndEnumProc() {
            public boolean callback(int hWnd, int lParam) throws UnsupportedEncodingException {
                if (User32.instance.IsWindowVisible(hWnd)) {
                    RECT r = new RECT();
                    User32.instance.GetWindowRect(hWnd, r);
                    if (r.left > -32000) {     // If it's not minimized
                        byte[] buffer = new byte[1024];
                        User32.instance.GetWindowTextA(hWnd, buffer, buffer.length);
                        String title = Native.toString(buffer);
                        inflList.add(new WindowInfo(hWnd, r, title));
                    }
                }
                return true;
            }
        }, 0);

        Collections.sort(inflList, new Comparator<WindowInfo>() {
            public int compare(WindowInfo o1, WindowInfo o2) {
                return order.indexOf(o1.hwnd)-order.indexOf(o2.hwnd);
            }
        });
        for (WindowInfo w : inflList) {
            String s = w.title;
//            s= StringUtils.replace();
            System.out.println(w.toStringCode());
        }
    }

    public static interface WndEnumProc extends StdCallLibrary.StdCallCallback {
        boolean callback(int hWnd, int lParam) throws UnsupportedEncodingException;
    }

    public static interface User32 extends StdCallLibrary {
        final User32 instance = (User32) Native.loadLibrary ("user32", User32.class);
        final int GW_HWNDNEXT = 2;

        boolean EnumWindows(WndEnumProc wndenumproc, int lParam);
        boolean IsWindowVisible(int hWnd);
        int GetWindowRect(int hWnd, RECT r);
        void GetWindowTextA(int hWnd, byte[] buffer, int buflen);
        int GetTopWindow(int hWnd);
        int GetWindow(int hWnd, int flag);
    }

    public static class RECT extends Structure {
        public int left, top, right, bottom;
    }

    public static class WindowInfo {
        public final int hwnd;
        public final RECT rect;
        public final String title;
        public WindowInfo(int hwnd, RECT rect, String title) {
            this.hwnd = hwnd;
            this.rect = rect;
            this.title = title;
        }

        public String toStringCode() throws UnsupportedEncodingException {
            String s = this.title;
            byte ptext[] = s.getBytes();
            String value = new String(ptext, "GBK");
            byte[] ptext2 = s.getBytes("GBK");
            String value2 = new String(ptext2, "GBK");
            return String.format("(%d,%d)-(%d,%d) : \"%s\"",
                    rect.left, rect.top,
                    rect.right, rect.bottom,
                    title);
        }

        public String toString() {
            return String.format("(%d,%d)-(%d,%d) : \"%s\"",
                    rect.left, rect.top,
                    rect.right, rect.bottom,
                    title);
        }
    }
}