package com.example.arcs.buttonHandlers;

import com.example.arcs.essentials.ArcHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DragArcButtonHandler implements EventHandler<MouseEvent> {

	private ArcHandler arcHandler;
	private Pane drawingPane;

	public DragArcButtonHandler(ArcHandler arcHandler, Pane drawingPane) {
		this.arcHandler = arcHandler;
		this.drawingPane = drawingPane;
	}

	@Override
	public void handle(MouseEvent event) {
		arcHandler.selectArc(arcHandler.getOrthyArc().getArc());
		arcHandler.makeArcDraggable(arcHandler.getOrthyArc().getArc());
	}
}
