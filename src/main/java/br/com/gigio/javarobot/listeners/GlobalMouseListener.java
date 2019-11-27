package br.com.gigio.javarobot.listeners;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import com.sun.jna.platform.win32.User32;
import com.sun.org.apache.bcel.internal.generic.StoreInstruction;
import com.test.CaptureUtil;
import com.test.StoreImageUtil;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import br.com.gigio.javarobot.domain.Coordinate;
import br.com.gigio.javarobot.handler.ScriptFileHandler;

public class GlobalMouseListener extends GenericListener implements NativeMouseListener{
	
	
	public GlobalMouseListener(File file){
		super(file);	
	}

	public void nativeMouseClicked(NativeMouseEvent mouseEvent) {
		ScriptFileHandler.writeTo(super.getFile(), new Coordinate(mouseEvent.getX(), mouseEvent.getY()));
	}

	public void nativeMousePressed(NativeMouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		try {
			BufferedImage bi = CaptureUtil.capture();
			StoreImageUtil.storePicure(bi);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		}
	}

	public void nativeMouseReleased(NativeMouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		
	}
}
