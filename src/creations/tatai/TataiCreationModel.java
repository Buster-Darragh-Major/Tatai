package creations.tatai;

import java.util.ArrayList;
import java.util.List;

import creations.cr.Creation;
import creations.ml.CreationModel;
import creations.tatai.TataiCreationBuilder.Level;

public class TataiCreationModel extends CreationModel{
	
	private List<Creation> _creations = new ArrayList<Creation>();
	private Level _level;
	
	// Constructor, default sets to level 1
	public TataiCreationModel() {
		_level = Level.Level1;
	}

	public void setLevel(Level level) {
		_level = level;
	}
	
	public void populateModel() {
		TataiCreationBuilder b = new TataiCreationBuilder();
		_creations.addAll(b.populateList(_level));
	}
	
	@Override
	public void addCreation(Creation creation) {
		_creations.add(creation);
	}

	@Override
	public void addCreation(String creationName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateModel() {
		// TODO Auto-generated method stub
		
	}

}
