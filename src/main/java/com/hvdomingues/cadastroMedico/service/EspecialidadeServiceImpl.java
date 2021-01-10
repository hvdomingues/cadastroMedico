package com.hvdomingues.cadastroMedico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.EspecialidadeRepository;
import com.hvdomingues.cadastroMedico.dao.jpa.MedicoEspecialidadeRepository;
import com.hvdomingues.cadastroMedico.domain.Especialidade;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.domain.MedicoEspecialidade;
import com.hvdomingues.cadastroMedico.dto.EspecialidadeDto;
import com.hvdomingues.cadastroMedico.exception.IllegalArgumentException;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {

	@Autowired
	MedicoEspecialidadeRepository medicoEspecialidadeRepository;
	
	@Autowired
	EspecialidadeRepository especialidadeRepository;

	@Override
	public List<EspecialidadeDto> entityToDto(List<MedicoEspecialidade> especialidades) {

		List<EspecialidadeDto> especialidadesDto = new ArrayList<EspecialidadeDto>();

		for (MedicoEspecialidade medicoEspecialidade : especialidades) {

			EspecialidadeDto especialidadeDto = new EspecialidadeDto();

			especialidadeDto.setId(medicoEspecialidade.getEspecialidade().getId());
			especialidadeDto.setNomeEspecialidade(medicoEspecialidade.getEspecialidade().getNomeEspecialidade());

			especialidadesDto.add(especialidadeDto);

		}

		return especialidadesDto;
	}

	
	@Override
	public List<MedicoEspecialidade> saveMedicoEspecialidade(List<EspecialidadeDto> especialidades, Medico medico) {

		if (especialidades == null || especialidades.size() < 2) {
			throw new IllegalArgumentException("O médico deve ter no mínimo duas especialidades.");
		} else {

			List<MedicoEspecialidade> medicoEspecialidadesSaved = new ArrayList<>();

			for (EspecialidadeDto especialidadeDto : especialidades) {

				MedicoEspecialidade medicoEspecialidade = new MedicoEspecialidade();
				Especialidade especialidade = especialidadeRepository.findById(especialidadeDto.getId()).get();

				if(especialidade == null) {
					throw new IllegalArgumentException("Não há nenhuma especialidade com o ID informado");
				}
				
				medicoEspecialidade.setEspecialidade(especialidade);
				medicoEspecialidade.setMedico(medico);

				medicoEspecialidadesSaved.add(medicoEspecialidade);

			}

			return (List<MedicoEspecialidade>) medicoEspecialidadeRepository.saveAll(medicoEspecialidadesSaved);
		}
	}

}
