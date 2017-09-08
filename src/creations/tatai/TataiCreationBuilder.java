package creations.tatai;

import javafx.scene.paint.Color;
/**
 * This class deals with generating creations
 * 
 * @author Nathan Cairns
 * @author Buster Darragh-Major
 *
 */
public class TataiCreationBuilder {
	/*MACROS*/
	
	/*Fields*/
	private int _number;
	private Color _bgColor;
	private Color _fontColor;
	
	public TataiCreationBuilder backgroundColor(Color color) {
		_bgColor = color;
		
		return this;
	}
	
	public TataiCreationBuilder fontColor(Color color) {
		_fontColor = color;
		
		return this;
	}
	
	public TataiCreationBuilder number(int number) {
		_number = number;
		
		return this;
	}
		
	public TataiCreation build() {
		return new TataiCreation(_number, _bgColor, _fontColor);
	}
	
}
