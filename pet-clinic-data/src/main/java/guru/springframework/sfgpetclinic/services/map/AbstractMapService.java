package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID> {

	protected Map<Long, T> map = new HashMap<>();

	Set<T> findAll() {
		return new HashSet<>(map.values());
	}

	T findById(ID id) {
		return map.get(id);
	}

	// metodo que usaremos para guardar un objeto en caso de que no sea nulo
	T save(T object) {
		
		// si el objeto no es nulo comprobamos su id
		if (object != null) {
			
			// si su id es nulo cogemos el siguiente, sino utilizamos ese 
			if (object.getId() == null)
				object.setId(getNextId());
			map.put(getNextId(), object);
		}
		
		// si el objeto es nulo lanzamos una excepcion ya que este no pude serlo
		else {
			throw new RuntimeException("Object can not be null");
		}

		return object;
	}

	void deleteById(ID id) {
		map.remove(id);
	}

	void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}

	// metodo que usaremos para dar el siguiente valor al Id del objeto
	private Long getNextId() {
		Long nextId = null;
		
		// intentamos darle un valor al id
		try {
			nextId = Collections.max(map.keySet()) + 1;
		}
		
		// si es nulo estableceremos el valor del id 1
		catch(NoSuchElementException e){
			nextId = 1L;
		}
		
		return nextId;
	}
}
