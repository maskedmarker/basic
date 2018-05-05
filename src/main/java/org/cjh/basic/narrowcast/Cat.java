package org.cjh.basic.narrowcast;

public class Cat extends AbstractAinimal {

	
	/* 
	 * 子类override父类方法，可以narrow cast
	 * 
	 */
	@Override
	public Cat product() {
		
		return new Cat();
	}

}
