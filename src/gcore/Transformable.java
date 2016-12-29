package gcore;

public interface Transformable {
	void applyTransform(Transform transform);
	Transform getModelTransform();
}
