package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Cliente;
import cl.healthmood.Health.Mood.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id){
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente guardar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente){
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long id){
        clienteRepository.deleteById(id);
    }

}
