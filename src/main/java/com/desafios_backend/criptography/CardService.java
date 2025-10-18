package com.desafios_backend.criptography;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardRepository cardRepository;

    public CardService() throws Exception {
        if (!RSAUtils.checkSOKeys()) {
            RSAUtils.keyPairGen();
        }
    }

    public CardDto createCard(CardDto cardDto) throws Exception {
        CardModel cardModel = this.modelMapper.map(cardDto, CardModel.class);
        cardModel.setCreditCardToken(RSAUtils.encrypt(cardModel.getCreditCardToken()));
        cardModel.setUserDocument(RSAUtils.encrypt(cardModel.getUserDocument()));
        CardModel savedCard = this.cardRepository.save(cardModel);

        return this.modelMapper.map(savedCard, CardDto.class);
    }

    public Collection<CardDto> getAllCards() {
        Collection<CardModel> cardModelCollection = this.cardRepository.findAll();
        Collection<CardModel> cardModelCollectionDecrypted = this.decryptMany(cardModelCollection);

        return cardModelCollectionDecrypted
                .stream()
                .map(cardModel -> this.modelMapper.map(cardModel, CardDto.class))
                .collect(Collectors.toList());
    }

    private Collection<CardModel> decryptMany(Collection<CardModel> cards) {

        return cards.stream().map(cardModel -> {
            try {
                cardModel.setCreditCardToken(RSAUtils.decrypt(cardModel.getCreditCardToken()));
                cardModel.setUserDocument(RSAUtils.decrypt(cardModel.getUserDocument()));
                return cardModel;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao descriptografar cartão", e);
            }
        }).toList();
    }
}
