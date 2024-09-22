package com.jokim.sivillage.api.wishlist.eventwishlist.application;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistResponseDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.infrastructure.EventWishlistRepository;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventWishlistServiceImpl implements EventWishlistService {

    private final EventWishlistRepository eventWishlistRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public void addEventWishlist(EventWishlistRequestDto eventWishlistRequestDto) {

        String uuid = jwtTokenProvider.validateAndGetUserUuid(eventWishlistRequestDto.getAccessToken());
        Long id =eventWishlistRepository.findByUuidAndEventCode(uuid, eventWishlistRequestDto.getEventCode())
                .map(EventWishlist::getId).orElse(null);

        eventWishlistRepository.save(eventWishlistRequestDto.toEntity(id, uuid, true));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EventWishlistResponseDto> getAllEventWishlists(String accessToken) {

        return eventWishlistRepository.findByUuidAndIsCheckedOrderByUpdatedAtDesc(
                jwtTokenProvider.validateAndGetUserUuid(accessToken), true)
                .stream().map(EventWishlistResponseDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public EventWishlistResponseDto getEventWishlistState(String accessToken, String eventCode) {

        Boolean isChecked = eventWishlistRepository.findByUuidAndEventCode(
                jwtTokenProvider.validateAndGetUserUuid(accessToken), eventCode)
                .map(EventWishlist::getIsChecked).orElse(false);

        return EventWishlistResponseDto.toDto(isChecked);
    }

    @Transactional
    @Override
    public void deleteEventWishlist(EventWishlistRequestDto eventWishlistRequestDto) {  // Soft Delete

        String uuid = jwtTokenProvider.validateAndGetUserUuid(eventWishlistRequestDto.getAccessToken());
        Long id = eventWishlistRepository.findByUuidAndEventCode(uuid, eventWishlistRequestDto.getEventCode())
                .map(EventWishlist::getId).orElse(null);

        if(id == null) return;
        eventWishlistRepository.save(eventWishlistRequestDto.toEntity(id, uuid, false));
    }

}
