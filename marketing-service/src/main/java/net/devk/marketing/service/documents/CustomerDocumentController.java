package net.devk.marketing.service.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.devk.marketing.service.ControllersConfig;
import net.devk.marketing.service.documents.dto.CreateNewCustomerDocumentResponseDTO;
import net.devk.marketing.service.model.CustomerDocument;

@RestController
@RequestMapping(path = ControllersConfig.API_PREFIX + "/customers")
public class CustomerDocumentController {

	@Autowired
	private DocumentService documentService;

	@PostMapping("{id}/documents")
	public ResponseEntity<CreateNewCustomerDocumentResponseDTO> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("documentTypeId") Long documentTypeId, @PathVariable("id") Long customerId) {

		CustomerDocument customerDocument = documentService.createCustomerDocument(customerId, documentTypeId, file);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new CreateNewCustomerDocumentResponseDTO(customerDocument.getId(),
						customerDocument.getRegisterDate(), customerDocument.getDocumentName(),
						customerDocument.getDocumentType().getId(), customerId));
	}

}
