package guru.springframework.sfgpetclinic.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import jakarta.validation.Valid;

/**
 * Created by jt on 7/22/18.
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

	private final OwnerService ownerService;
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	// metodo que usaremos para no permitir que el usuario
	// modifique el id del propietario a la hora de
	// introducirlo en la base de datos
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	// metodo que usaremos para mostrar la pagina para buscar owners
	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}

	// metodo que usaremos para procesar la informacion
	// del owner que quiere buscar el usuario
	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

		if (results == null || results.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		} else if (results.size() == 1) {
			// 1 owner found
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		} else {
			// multiple owners found
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}

	// metodo que usaremos para mostrar el owner que se ha buscado
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownersDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}

	  @GetMapping("/new")
	    public String initCreationForm(Model model) {
	        model.addAttribute("owner", Owner.builder().build());
	        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	    }

	    @PostMapping("/new")
	    public String processCreationForm(@Valid Owner owner, BindingResult result) {
	        if (result.hasErrors()) {
	            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	        } else {
	            Owner savedOwner =  ownerService.save(owner);
	            return "redirect:/owners/ownersList" + savedOwner.getId();
	        }
	    }

	    @GetMapping("/{ownerId}/edit")
	    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
	        model.addAttribute(ownerService.findById(ownerId));
	        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	    }

	    @PostMapping("/{ownerId}/edit")
	    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
	        if (result.hasErrors()) {
	            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	        } else {
	            owner.setId(ownerId);
	            Owner savedOwner = ownerService.save(owner);
	            return "redirect:/owners/" + savedOwner.getId();
	        }
	    }

}