package me.lucko.luckperms.standalone.util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ColoredLine extends HBox {

	private final StringProperty lineText;
	private final ObjectProperty<Font> font;

	private final char split;

	public ColoredLine(char split, String line) {
		this.split = split;
		lineText = new SimpleStringProperty();
		lineText.addListener(change -> setupLine());
		lineText.setValue(line);
		font = new SimpleObjectProperty<>();
		font.addListener(change -> setupLine());
	}

	public ColoredLine(String line) {
		this('&', line);
	}

	private void setupLine() {
		getChildren().clear();
		String stringToDo = lineText.get();
		do {
			stringToDo = parseString(stringToDo);
		} while (!stringToDo.isEmpty());
	}

	private String parseString(String str) {
		int startPos = indexOfFirstColorCode(str);
		Color color;
		int endPos = indexOfFirstColorCode(str.substring(startPos + 2));
		if (startPos > 0) {
			startPos = 0;
			endPos = indexOfFirstColorCode(str);
			color = Color.BLACK;
		} else if (startPos == 0) {
			String code = str.substring(startPos, startPos + 2);
			str = str.substring(startPos + 2);
			color = ColorCodes.getCode(code).getFXColor();
		} else {
			startPos = 0;
			color = Color.BLACK;
		}
		if (endPos == -1) {
			endPos = str.length();
		}
		addPart(str.substring(startPos, endPos), color);
		return str.substring(endPos);
	}

	private void addPart(String str, Color color) {
		Label lbl = new Label(str);
		lbl.setTextFill(color);
		if (font != null) {
			lbl.setFont(font.get());
		}
		getChildren().add(lbl);
	}

	private int indexOfFirstColorCode(String rawLine) {
		int ret = Integer.MAX_VALUE;
		for (ColorCodes color : ColorCodes.values()) {
			String id = "" + split + color.identifier;
			if (rawLine.contains(id)) {
				ret = Math.min(rawLine.indexOf(id), ret);
			}
		}
		return ret == Integer.MAX_VALUE ? -1 : ret;
	}

	public StringProperty lineTextProperty() {
		return lineText;
	}

	public String getLineText() {
		return lineText.get();
	}

	public void setLineText(String lineText) {
		this.lineText.set(lineText);
	}

	public ObjectProperty<Font> fontProperty() {
		return font;
	}

	public Font getFont() {
		return font.get();
	}

	public void setFont(Font font) {
		this.font.set(font);
	}
}
