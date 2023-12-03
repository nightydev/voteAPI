package ec.voto.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.voto.api.domain.Lista;
import ec.voto.api.dto.ListaDTO;
import ec.voto.api.repository.ListaPersistence;

@Service
public class ListaService extends GenericCrudServiceImpl<Lista, ListaDTO> {

    @Autowired
    private ListaPersistence repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Lista> find(ListaDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public Lista mapToDomain(ListaDTO dto) {
        return modelMapper.map(dto, Lista.class);
    }

    @Override
    public ListaDTO mapToDto(Lista domain) {
        return modelMapper.map(domain, ListaDTO.class);
    }

}
